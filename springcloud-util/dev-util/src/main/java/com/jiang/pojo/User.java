package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 77628
 */
@Data
public class User  implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
