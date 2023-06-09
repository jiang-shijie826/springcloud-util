package com.jiang.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 77628
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String nickName;
    private String phoneNumber;
    private String password;

    @TableField(exist=false)
    private String account;

    @TableField(exist=false)
    private String checkCode;

}
