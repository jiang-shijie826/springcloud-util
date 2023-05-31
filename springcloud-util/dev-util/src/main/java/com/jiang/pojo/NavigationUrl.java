package com.jiang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 导航地址
 */
@Data
public class NavigationUrl  implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String icoSrc;
    private String name;
    private String description;
    private String url;
    private int count;
    private String tag;

    @TableField(exist=false)
    private Double score;

}
