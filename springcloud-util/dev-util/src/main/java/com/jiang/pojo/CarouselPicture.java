package com.jiang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 图片轮播
 */
@Data
public class CarouselPicture implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String picName;
    private String picSrc;

}
