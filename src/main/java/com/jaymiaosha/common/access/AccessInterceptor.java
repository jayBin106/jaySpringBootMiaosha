package com.jaymiaosha.common.access;

import com.alibaba.fastjson.JSON;
import com.jaymiaosha.common.MiaoShaUserKey;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.result.CodeMsg;
import com.jaymiaosha.result.Result;
import com.jaymiaosha.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import static com.jaymiaosha.common.MiaoShaUserKey.getMiaoshaCount;
import static com.jaymiaosha.common.constant.ConstantCookie.COOKI_NAME_TOKEN;

/**
 * 拦截器实现接口限流
 * Created by lenovo on 2018/8/2.
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //将用户信息放入到当前线程中
            MiaoshaUser user = getUser(request);
            UserContext.setUser(user);
            HandlerMethod handler1 = (HandlerMethod) handler;
            //接口访问限流
            AccessLimit accessLimit = handler1.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int maxCount = accessLimit.maxCount();
            int seconds = accessLimit.seconds();
            //判断接口是否在登录情况下访问
            boolean b = accessLimit.needLogin();
            if (b) {
                MiaoshaUser user1 = UserContext.getUser();
                if (user1 == null) {
                    render(response, CodeMsg.SESSION_ERROR);
                    return false;
                }
            }
            //接口限流操作
            //访问的url
            String requestURI = request.getRequestURI();
            MiaoShaUserKey shaUserKey = getMiaoshaCount(seconds);
            //访问次数
            String realUrlKey = shaUserKey.getPrefix() + user.getId() + "_" + requestURI;
            String s = redisUtil.get(realUrlKey);
            Integer count = Integer.valueOf(s == null ? "0" : s);
            if (count == 0) {
                redisUtil.set(shaUserKey, user.getId() + "_" + requestURI, 1 + "");
            } else if (count < maxCount) {
                redisUtil.increment(realUrlKey, 1);
            } else {
                render(response, CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }
        return true;
    }

    /**
     * 输出浏览器提示信息
     *
     * @param response
     * @param cm
     * @throws Exception
     */
    private void render(HttpServletResponse response, CodeMsg cm) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    /**
     * 从token中获取信息
     *
     * @param request
     * @return
     */
    private MiaoshaUser getUser(HttpServletRequest request) {
        String paramToken = request.getParameter(COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, COOKI_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        String s = redisUtil.get(MiaoShaUserKey.token.getPrefix() + token);
        MiaoshaUser miaoshaUser = RedisUtil.stringToBean(s, MiaoshaUser.class);
        return miaoshaUser;
    }

    /**
     * 从cookie中获取信息
     *
     * @param request
     * @param cookiName
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
