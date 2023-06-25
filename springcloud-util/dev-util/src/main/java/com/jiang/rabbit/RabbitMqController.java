package com.jiang.rabbit;

import com.jiang.constant.Result;
import com.jiang.service.RabbitMQService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangsj
 * @create 2023/6/20
 * @desc
 */
@RestController
@RequestMapping("/mall/rabbitmq")
public class RabbitMqController {
    @Resource
    private RabbitMQService rabbitMqService;
    /**
     * 发送消息
     * @author jiangsj
     */
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) {
        return rabbitMqService.sendMsg(msg);
    }

    /**
     * 发送邮箱验证码
     * @param username /
     * @return /
     */
    @GetMapping("/users/code")
    public Result<?> sendCode(String username) {
        return rabbitMqService.sendCode(username);
    }

}
