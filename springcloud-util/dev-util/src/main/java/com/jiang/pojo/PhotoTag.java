package com.jiang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoTag implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private String tagId;
    private String tagName;
    private String sort;
}
