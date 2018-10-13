package com.jaymiaosha.controller;

import com.jaymiaosha.dao.MiaoshaUserMapper;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by lenovo on 2018/10/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MiaoshaUserMapper miaoshaUserMapper;

    @PostMapping("/register")
    @ResponseBody
    public String add(String id, String password, String openid, String nickname) {
        MiaoshaUser miaoshaUser = new MiaoshaUser();
        miaoshaUser.setId(Long.valueOf(id));
        //password加密
        String passToFormPass = MD5Util.inputPassToDbPass(password, "1a2b3c4d");
        miaoshaUser.setPassword(passToFormPass);
        miaoshaUser.setSalt("1a2b3c4d");
        miaoshaUser.setRegisterdate(new Date());
        miaoshaUser.setOpenid(openid);
        miaoshaUser.setNickname(nickname);
        //判断是否已存在
        MiaoshaUser miaoshaUser1 = miaoshaUserMapper.selectByPrimaryKey(Long.valueOf(id));
        if (miaoshaUser1 != null) {
            miaoshaUserMapper.updateByPrimaryKeySelective(miaoshaUser);
            return "更新成功";
        } else {
            miaoshaUserMapper.insertSelective(miaoshaUser);
            return "注册成功";
        }
    }
}
