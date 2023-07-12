package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoWall  implements Serializable {
    private String id;
    private String title;
    private String imgUrl;
    private String tagId;
    private String visibleFlag;
}
