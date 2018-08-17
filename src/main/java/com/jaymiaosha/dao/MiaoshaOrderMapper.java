package com.jaymiaosha.dao;

import com.jaymiaosha.pojo.MiaoshaOrder;
import com.jaymiaosha.pojo.MiaoshaOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MiaoshaOrderMapper {
    int countByExample(MiaoshaOrderExample example);

    int deleteByExample(MiaoshaOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MiaoshaOrder record);

    int insertSelective(MiaoshaOrder record);

    List<MiaoshaOrder> selectByExample(MiaoshaOrderExample example);

    MiaoshaOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MiaoshaOrder record, @Param("example") MiaoshaOrderExample example);

    int updateByExample(@Param("record") MiaoshaOrder record, @Param("example") MiaoshaOrderExample example);

    int updateByPrimaryKeySelective(MiaoshaOrder record);

    int updateByPrimaryKey(MiaoshaOrder record);
}