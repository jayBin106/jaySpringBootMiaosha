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
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        boolean equals = MiaoshaUser.class.equals(parameterType);
        return equals;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        MiaoshaUser user = UserContext.getUser();
        return user;
    }
}
