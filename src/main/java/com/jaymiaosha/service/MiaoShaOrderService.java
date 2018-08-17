package com.jaymiaosha.service;

import com.jaymiaosha.pojo.MiaoshaOrder;
import com.jaymiaosha.pojo.MiaoshaUser;
import com.jaymiaosha.pojo.OrderInfo;
import com.jaymiaosha.pojoVo.GoodsVo;

/**
 * Created by lenovo on 2018/8/1.
 */
public interface MiaoShaOrderService {
    /**
     * 通过用户id和商品id获取订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId);

    /**
     * 创建订单
     *
     * @param user
     * @param goods
     * @return
     */
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);

    /**
     * 秒杀下单
     *
     * @param user
     * @param goods
     * @return
     */
    public OrderInfo startMiaoshaOrder(MiaoshaUser user, GoodsVo goods);

    /**
     * 判断秒杀结果
     *
     * @param id
     * @param goodsId
     * @return
     */
    long getMiaoshaResult(Long id, long goodsId);
}
