package com.jaymiaosha.controller;

import com.jaymiaosha.common.access.AccessLimit;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojoVo.GoodsVo;
import com.jaymiaosha.result.CodeMsg;
import com.jaymiaosha.result.Result;
import com.jaymiaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/31.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 商品列表
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/to_list")
    public String to_list(ModelMap modelMap) {
        List<GoodsVo> miaoshaGoods = goodsService.selectGoodesVo(new HashMap());
        modelMap.put("goodsList", miaoshaGoods);
        return "goods_list";
    }

    /**
     * 商品详情
     *
     * @param miaoshaUser
     * @param modelMap
     * @param goodsId
     * @return
     */
    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)   //接口访问限流
    @RequestMapping("/goods_detail/{goodsId}")
    @ResponseBody
    public Result<ModelMap> goods_detail(MiaoshaUser miaoshaUser, ModelMap modelMap, @PathVariable(value = "goodsId") Integer goodsId) {
        if (miaoshaUser == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        System.out.println(1111111);
        Map integerHashMap = new HashMap();
        integerHashMap.put("id", goodsId);
        List<GoodsVo> miaoshaGoods = goodsService.selectGoodesVo(integerHashMap);
        GoodsVo goodsVo = miaoshaGoods != null && miaoshaGoods.size() > 0 ? miaoshaGoods.get(0) : null;
        modelMap.put("goods", goodsVo);
        //根据秒杀时间判断
        //秒杀状态
        int miaoshaStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        if (goodsVo != null) {
            long starTime = goodsVo.getStartDate().getTime();
            long endTime = goodsVo.getEndDate().getTime();
            long nowTime = System.currentTimeMillis();
            if (starTime > nowTime) {           //秒杀还没开始，倒计时
                remainSeconds = (int) ((starTime - nowTime) / 1000);
            } else if (endTime < nowTime) {      //秒杀还没开始，倒计时
                miaoshaStatus = 2;
                remainSeconds = -1;
            } else {
                miaoshaStatus = 1;
                remainSeconds = 0;
            }
        }
        modelMap.put("miaoshaStatus", miaoshaStatus);
        modelMap.put("remainSeconds", remainSeconds);
        return Result.success(modelMap);
    }
}
