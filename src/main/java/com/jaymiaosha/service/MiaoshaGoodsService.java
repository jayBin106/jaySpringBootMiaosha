package com.jaymiaosha.service;

import com.jaymiaosha.pojo.MiaoshaGoods;
import com.jaymiaosha.pojo.MiaoshaGoodsExample;
import com.jaymiaosha.pojo.MiaoshaUser;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by lenovo on 2018/7/31.
 */
public interface MiaoshaGoodsService {
    List<MiaoshaGoods> selectByExample(MiaoshaGoodsExample example);

    MiaoshaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaGoods record);

    public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId, HttpSession httpSession);

}
