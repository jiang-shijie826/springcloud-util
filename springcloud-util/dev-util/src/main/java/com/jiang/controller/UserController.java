package com.jiang.controller;


import com.jiang.constant.Result;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 校验用户登录
     * @param user /
     * @return /
     */
    @PostMapping("login")
    private Result<User> checkUserLogin(@RequestBody User user) {
        return userService.checkUserLogin(user);
    }

    /**
     * 查询所有用户
     * @return /
     */
    @GetMapping("query")
    private List<User> queryAllUser() {
        return userService.queryAllUser();
    }


    /**
     * 校验验证码
     * @param user /
     * @return /
     */
    @PostMapping("checkCode")
    public String checkCode(@RequestBody User user) {
        return userService.checkCode(user);
    }

}
