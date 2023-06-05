package com.jiang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.constant.Result;
import com.jiang.mapper.UserMapper;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> checkUserLogin(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", user.getAccount())
        .or()
        .eq("email", user.getAccount());
        User tempUser = userMapper.selectOne(wrapper);
        if (Objects.isNull(tempUser)) {
            return ResultUtil.failureMsg("用户名或密码错误!");
        }
        if (tempUser.getPhoneNumber().equals(user.getAccount())
                || tempUser.getEmail().equals(user.getAccount())) {
            if (tempUser.getPassword().equals(user.getPassword())) {
                return ResultUtil.success("登录成功!", tempUser);
            }
        }
        return ResultUtil.failureMsg("用户名或密码错误!");
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectList(null);
    }
}
