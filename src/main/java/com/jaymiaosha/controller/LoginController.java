package com.jaymiaosha.controller;

import com.jaymiaosha.common.access.AccessLimit;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.result.CodeMsg;
import com.jaymiaosha.result.Result;
import com.jaymiaosha.service.MiaoshaUserService;
import com.jaymiaosha.util.MD5Util;
import com.jaymiaosha.util.UUidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lenovo on 2018/7/31.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String to_login() {
        return "login";
    }

    @AccessLimit(seconds = 10, maxCount = 10, needLogin = false)
    @ResponseBody
    @PostMapping("/do_login")
    public Result<String> do_login(long mobile, String password, HttpSession httpSession, HttpServletResponse response) {
        MiaoshaUser miaoshaUser = miaoshaUserService.selectByPrimaryKey(mobile);
        String sessionId = "";
        if (miaoshaUser != null) {
            String password1 = miaoshaUser.getPassword();
            String salt = miaoshaUser.getSalt();
            String s = MD5Util.formPassToDBPass(password, salt);
            if (s.equals(password1)) {
                String token = UUidUtil.get();
                miaoshaUserService.addCookie(response, token, miaoshaUser);
                return Result.success(sessionId);
            } else {
                return Result.error(CodeMsg.BIND_ERROR);
            }
        } else {
            return Result.error(CodeMsg.BIND_ERROR);
        }
    }
}
