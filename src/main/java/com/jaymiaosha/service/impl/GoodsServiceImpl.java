package com.jaymiaosha.service.impl;

import com.jaymiaosha.dao.GoodsMapper;
import com.jaymiaosha.pojo.Goods;
import com.jaymiaosha.pojo.GoodsExample;
import com.jaymiaosha.pojoVo.GoodsVo;
import com.jaymiaosha.service.GoodsService;
import com.jaymiaosha.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/31.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    int n = 0;
    String token = "token";
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<Goods> selectByExample(GoodsExample example) {
        return goodsMapper.selectByExample(example);
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsVo> selectGoodesVo(Map map) {
        return goodsMapper.selectGoodesVo(map);
    }

    @Async
    public void testAsyc1() {
        try {
            Thread.sleep(2000);
            System.out.println("线程一时间：" + System.currentTimeMillis());
            n++;
            redisUtil.leftPush(token, n + "A");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Async
    public void testAsyc2() {
        System.out.println("线程二时间：" + System.currentTimeMillis());
        n++;
        redisUtil.leftPush(token, n + "B");
    }
}
