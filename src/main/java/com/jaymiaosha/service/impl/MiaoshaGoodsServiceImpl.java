package com.jaymiaosha.service.impl;

import com.jaymiaosha.dao.MiaoshaGoodsMapper;
import com.jaymiaosha.pojo.MiaoshaGoods;
import com.jaymiaosha.pojo.MiaoshaGoodsExample;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.service.MiaoshaGoodsService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import static com.jaymiaosha.common.MiaoShaUserKey.tokenPic;

/**
 * Created by lenovo on 2018/7/31.
 */
@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {
    @Autowired
    MiaoshaGoodsMapper miaoshaGoodsMapper;
    @Autowired
    RedisUtil redisUtil;


    public List<MiaoshaGoods> selectByExample(MiaoshaGoodsExample example) {
        return miaoshaGoodsMapper.selectByExample(example);
    }

    @Override
    public MiaoshaGoods selectByPrimaryKey(Long id) {
        return miaoshaGoodsMapper.selectByPrimaryKey(id);
    }

    //秒杀图片验证生成方法
    @Override
    public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId, HttpSession httpSession) {
//        if (user == null || goodsId <= 0) {
//            return null;
//        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        String httpSessionId = httpSession.getId();
        redisUtil.del(tokenPic.getPrefix() + user.getId() + "_" + goodsId);
        redisUtil.set(tokenPic, user.getId() + "_" + goodsId, rnd + "");
//        redisService.set(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
        //输出图片
        return image;
    }

    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        return exp;
    }

    private static char[] ops = new char[]{'+', '-', '*'};

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(MiaoshaGoods record) {
        return miaoshaGoodsMapper.updateByPrimaryKeySelective(record);
    }
}
