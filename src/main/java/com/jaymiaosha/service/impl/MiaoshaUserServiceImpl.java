package com.jaymiaosha.service.impl;

import com.jaymiaosha.common.MiaoShaUserKey;
import com.jaymiaosha.dao.MiaoshaUserMapper;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojo.MiaoshaUserExample;
import com.jaymiaosha.service.MiaoshaUserService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.jaymiaosha.common.constant.ConstantCookie.COOKI_NAME_TOKEN;

/**
 * Created by lenovo on 2018/7/31.
 */
@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    @Autowired
    MiaoshaUserMapper miaoshaUserMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<MiaoshaUser> selectByExample(MiaoshaUserExample example) {
        return miaoshaUserMapper.selectByExample(example);
    }

    @Override
    public MiaoshaUser selectByPrimaryKey(Long id) {
        return miaoshaUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisUtil.set(MiaoShaUserKey.token, token, RedisUtil.beanToString(user));
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
