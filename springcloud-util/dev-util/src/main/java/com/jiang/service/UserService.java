package com.jiang.service;

import com.jiang.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */
@Component
public interface UserService {
    /**
     * 校验用户登录
     * @param user /
     * @return /
     */
    User checkUserLogin(User user);
}
