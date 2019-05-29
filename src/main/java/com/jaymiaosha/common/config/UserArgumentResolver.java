package com.jaymiaosha.common.config;

import com.jaymiaosha.common.access.UserContext;
import com.jaymiaosha.pojo.MiaoshaUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有构造参数的都会进这个方法，根据参数类型，赋值
 * Created by lenovo on 2018/8/1.
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    //用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument。
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        boolean equals = MiaoshaUser.class.equals(parameterType);
        return equals;
    }

    //真正用于处理参数分解的方法，返回的Object就是controller方法上的形参对象。
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        MiaoshaUser user = UserContext.getUser();
        return user;
    }
}
