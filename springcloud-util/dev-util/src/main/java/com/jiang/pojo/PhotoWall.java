package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoWall  implements Serializable {
    private String id;
    private String title;
    private String img_url;
    private String tag_id;
    private String visible_flag;
}
