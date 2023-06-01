package com.jiang.controller;


import com.jiang.pojo.User;
import com.jiang.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @ResponseBody
    @PostMapping("checkUser")
    private User checkUserLogin(@RequestBody User user) {
        return userService.checkUserLogin(user);
    }

}
