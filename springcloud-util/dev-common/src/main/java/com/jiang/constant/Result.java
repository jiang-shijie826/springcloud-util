package com.jiang.constant;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jiangsj
 * @create 2023/6/2
 * @desc
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private Integer status;

    private Integer code;

    private String msg;

    private T data;

}
