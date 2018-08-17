package com.jaymiaosha.service.impl;

import com.jaymiaosha.common.MiaoShaUserKey;
import com.jaymiaosha.dao.MiaoshaOrderMapper;
import com.jaymiaosha.dao.OrderInfoMapper;
import com.jaymiaosha.pojo.*;
import com.jaymiaosha.pojoVo.GoodsVo;
import com.jaymiaosha.service.MiaoShaOrderService;
import com.jaymiaosha.service.MiaoshaGoodsService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/8/1.
 */
@Service
public class MiaoShaOrderServiceImpl implements MiaoShaOrderService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    MiaoshaOrderMapper miaoshaOrderMapper;

    /**
     * 通过用户id和商品id获取订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        String s = redisUtil.get(MiaoShaUserKey.getMiaoshaOrder.getPrefix() + userId + "_" + goodsId);
        MiaoshaOrder miaoshaOrder = RedisUtil.stringToBean(s, MiaoshaOrder.class);
        return miaoshaOrder;
    }

    /**
     * 创建订单
     *
     * @param user
     * @param goods
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryaddrid(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsname());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderInfoMapper.insert(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrderMapper.insert(miaoshaOrder);
        String miaoshaOrderStr = RedisUtil.beanToString(miaoshaOrder);
        //放入redis
        redisUtil.set(MiaoShaUserKey.getMiaoshaOrder, user.getId() + "_" + goods.getId(), miaoshaOrderStr);
        return orderInfo;
    }

    /**
     * 秒杀下单
     *
     * @param user
     * @param goods
     * @return
     */
    @Override
    public OrderInfo startMiaoshaOrder(MiaoshaUser user, GoodsVo goods) {
        boolean bl = true;
        //减库存
        MiaoshaGoodsExample example = new MiaoshaGoodsExample();
        MiaoshaGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goods.getId());
        criteria.andStockCountGreaterThan(0);
        List<MiaoshaGoods> goods1 = miaoshaGoodsService.selectByExample(example);
        if (goods1 != null && goods1.size() > 0) {
            for (MiaoshaGoods miaoshaGoods : goods1) {
                miaoshaGoods.setStockCount(goods.getStockCount() - 1);
                int i = miaoshaGoodsService.updateByPrimaryKeySelective(miaoshaGoods);
                if (i <= 0) {
                    bl = false;
                }
            }
        }
        if (bl) {
            //创建订单
            OrderInfo order = createOrder(user, goods);
            return order;
        }
        return null;
    }

    /**
     * 判断秒杀结果
     *
     * @param id
     * @param goodsId
     * @return
     */
    @Override
    public long getMiaoshaResult(Long id, long goodsId) {
        MiaoshaOrder miaoshaOrder = getMiaoshaOrderByUserIdGoodsId(id, goodsId);
        if (miaoshaOrder != null) {
            return miaoshaOrder.getGoodsId();
        }
        return 0;
    }
}
