package com.jaymiaosha.controller.winxin;

import com.alibaba.fastjson.JSONObject;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojo.MiaoshaUserExample;
import com.jaymiaosha.service.MiaoshaUserService;
import com.jaymiaosha.util.winxin.WinXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lenovo on 2018/10/13.
 * 微信网站授权登录--用的是微信测试帐号
 */
@Controller
@RequestMapping("/weixin")
public class WinXinLogin {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/index")
    public String index() {
        return "weixinIndex";
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = WinXinUtil.SERVICE_URL + "/weixin/callBack";
        String encode = URLEncoder.encode(backUrl, "UTF-8");
        String scope = "snsapi_userinfo";
        //替换参数
        String getCodeUrl = WinXinUtil.GET_CODE_URL.replace("APPID", WinXinUtil.appId).replace("REDIRECT_URI", encode).replace("SCOPE", scope);
        //重定向
        response.sendRedirect(getCodeUrl);
    }

    /**
     * 获取code后，请求以下链接获取access_token
     *
     * @param code
     */
    @RequestMapping("/callBack")
    public String callBack(String code, Model model) {
        String getAccessTokenUrl = WinXinUtil.GET_ACCESS_TOKEN_URL.replace("APPID", WinXinUtil.appId).replace("SECRET", WinXinUtil.appsecret).replace("CODE", code);
        JSONObject jsonObject = WinXinUtil.doGetStr(getAccessTokenUrl);
        System.out.println("CallBack 数据：" + jsonObject);
        System.out.println("开始获取用户信息");
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        //获取微信用户信息
        String getuserinfourl = WinXinUtil.GET_USERINFO_BY_OPENID_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
        JSONObject userINfo = WinXinUtil.doGetStr(getuserinfourl);
        //查询微信用户是否绑定用户表
        MiaoshaUserExample example = new MiaoshaUserExample();
        MiaoshaUserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        List<MiaoshaUser> miaoshaUsers = miaoshaUserService.selectByExample(example);
        //用户已经绑定
        if (miaoshaUsers != null && miaoshaUsers.size() > 0) {
            String nickname = userINfo.getString("nickname");
            String userNickName = miaoshaUsers.get(0).getNickname();
            if (nickname.equals(userNickName)) {
                model.addAttribute("user", userINfo);
                return "userInfo";
            } else {
                //进入帐号绑定页面
                model.addAttribute("user", userINfo);
                return "register";
            }
        } else {
            //进入帐号绑定页面
            model.addAttribute("user", userINfo);
            return "register";
        }
    }
}
