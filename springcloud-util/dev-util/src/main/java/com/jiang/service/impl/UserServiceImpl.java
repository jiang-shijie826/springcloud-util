package com.jiang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.mapper.UserMapper;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User checkUserLogin(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phoneNumber", user.getPhoneNumber());
        User tempUser = userMapper.selectOne(wrapper);
        //校验用户名是否存在
        //校验密码是否相等
        return null;
    }
}
