package com.jiang.service;

import org.springframework.stereotype.Component;

/**
 * @author jiangsj
 * @create 2023/6/20
 * @desc
 */
@Component
public interface RabbitMQService {
    String sendMsg(String msg);

    String sendCode(String username);
}
