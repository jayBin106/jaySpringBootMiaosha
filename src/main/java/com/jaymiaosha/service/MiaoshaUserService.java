package com.jaymiaosha.service;

import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojo.MiaoshaUserExample;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lenovo on 2018/7/31.
 */
public interface MiaoshaUserService {
    List<MiaoshaUser> selectByExample(MiaoshaUserExample example);

    MiaoshaUser selectByPrimaryKey(Long id);

    void addCookie(HttpServletResponse response, String sessionId, MiaoshaUser user);
}
