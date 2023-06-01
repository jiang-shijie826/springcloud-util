package com.jiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jiangsj
 * @create 2023/6/1
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BingPicture implements Serializable {
    private String startDate;
    private String url;
}
