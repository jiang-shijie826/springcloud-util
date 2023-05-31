package com.jiang.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 77628
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class HelloWordController {

    @Value("${dev-util.test.userId}")
    private String userId;

    @GetMapping("getUserConfigTest")
    public String getUserConfigTest(){
        return userId != null ? userId : "未获取到配置";
    }
}
