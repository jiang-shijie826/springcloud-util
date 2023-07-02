package com.jiang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.constant.Result;
import com.jiang.mapper.UserMapper;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result<User> checkUserLogin(User user) {
        User tempUser = getUser(user);
        if (Objects.isNull(tempUser)) {
            return ResultUtil.failureMsg("用户名或密码错误!");
        }
        if (tempUser.getEmail().equals(user.getAccount())) {
            if (tempUser.getPassword().equals(user.getPassword())) {
                return ResultUtil.success("登录成功!", tempUser);
            }
        }
        return ResultUtil.failureMsg("用户名或密码错误!");
    }

    private User getUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", user.getAccount())
        .or()
        .eq("email", user.getAccount());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public Result<?> registerUser(User user) {
        Object members = redisTemplate.opsForValue().get("code:"+user.getAccount());
        //判断验证码是否过期
        if (Objects.isNull(members)) {
            return ResultUtil.failureMsg("验证码已失效!");
        }
        //判断验证码是否相等
        if (!user.getCheckCode().equals(members)) {
            return ResultUtil.failureMsg("您的验证码有误!");
        }
        //判断用户是否已经注册
        User tempUser = getUser(user);
        if (!Objects.isNull(tempUser)) {
            return ResultUtil.failureMsg("该用户已存在!");
        }
        //保存用户
        user.setEmail(user.getAccount());
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return ResultUtil.success("用户注册成功!", user);
        }
        return ResultUtil.failureMsg("用户注册失败!");
    }
}
