package com.jaymiaosha.service;

import com.jaymiaosha.pojo.User;
import com.jaymiaosha.pojo.UserExample;

import java.util.List;

/**
 * Created by lenovo on 2018/7/31.
 */
public interface UserService {

    User selectByPrimaryKey(Integer id);

    List<User> selectByExample(UserExample example);
}
