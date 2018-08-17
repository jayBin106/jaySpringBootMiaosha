package com.jaymiaosha.service;

import com.jaymiaosha.pojo.Goods;
import com.jaymiaosha.pojo.GoodsExample;
import com.jaymiaosha.pojoVo.GoodsVo;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/31.
 */
public interface GoodsService {
    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Long id);

    List<GoodsVo> selectGoodesVo(Map map);

    public void testAsyc1();

    public void testAsyc2();
}
