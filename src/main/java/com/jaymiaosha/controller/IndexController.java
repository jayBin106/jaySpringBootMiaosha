package com.jaymiaosha.controller;

import com.jaymiaosha.common.config.YmlProperties;
import com.jaymiaosha.pojo.User;
import com.jaymiaosha.service.GoodsService;
import com.jaymiaosha.service.UserService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2018/7/31.
 */
@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    YmlProperties ymlProperties;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisUtil redisUtil;

    @ResponseBody
    @RequestMapping("/index")
    public String get() {
        System.out.println("Host:" + ymlProperties.getHost());
        User user = userService.selectByPrimaryKey(1);
        System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝多线程测试＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        for (int i = 0; i < 9; i++) {
            goodsService.testAsyc1();
            goodsService.testAsyc2();
        }
        Long aLong = redisUtil.listSize("token");
        System.out.println("tokenList长度：" + aLong);
        for (int i = 0; i < aLong; i++) {
            String token = redisUtil.rightPop("token");
            System.out.println("token:" + token);
        }
        return user.getName();
    }
}
