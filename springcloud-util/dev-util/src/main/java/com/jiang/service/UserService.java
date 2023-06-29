package com.jiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.constant.Result;
import com.jiang.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */
@Component
public interface UserService extends IService<User> {
    /**
     * 校验用户登录
     * @param user /
     * @return /
     */
    Result<User> checkUserLogin(User user);

    /**
     * 查询所有用户
     * @return /
     */
    List<User> queryAllUser();

    /**
     * 校验验证码
     * @param user /
     * @return /
     */
    Result<?> registerUser(User user);
}
