package com.jiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 77628
 */
@SpringBootApplication
@MapperScan("com.jiang.mapper")
@EnableCaching
public class DevUtilApplication {
    public static void main( String[] args ) {
        SpringApplication.run(DevUtilApplication.class, args);
    }
}
