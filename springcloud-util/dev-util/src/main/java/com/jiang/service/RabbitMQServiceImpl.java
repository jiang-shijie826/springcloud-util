package com.jiang.service;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import com.jiang.constant.RabbitMQConfig;
import com.jiang.pojo.EmailDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangsj
 * @create 2023/6/20
 * @desc
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    /**
     * 日期格式化
     */
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 验证码过期时间
     */
    public static final long CODE_EXPIRE_TIME = 5 * 60;

    /**
     * 验证码
     */
    public static final String USER_CODE_KEY = "code:";

    @Autowired
    private Producer checkCode;

    @Override
    public String sendMsg(String msg) {
        try {
            String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            String sendTime = SDF.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING, map);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public String sendCode(String username) {
        // 校验账号是否合法
        if (!checkEmail(username)) {
            return "请输入正确邮箱";
        }
        // 生成六位随机验证码发送
        String code = checkCode.createText();
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("【冰凉西瓜】验证消息")
                .content("登录操作，验证码："+ code + "，切勿将验证码泄露给他人，本条验证码有效期5分钟。")
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING,
                new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        //将验证码存入redis，设置过期时间为5分钟
        redisTemplate.opsForValue().set(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME, TimeUnit.SECONDS);
        return "验证码发送成功!";
    }

    /**
     * 检测邮箱是否合法
     *
     * @param username 用户名
     * @return 合法状态
     */
    public static boolean checkEmail(String username) {
        String rule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(rule);
        //正则表达式的匹配器
        Matcher m = p.matcher(username);
        //进行正则匹配
        return m.matches();
    }

}
