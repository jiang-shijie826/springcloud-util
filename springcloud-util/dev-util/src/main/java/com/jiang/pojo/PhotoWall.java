package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoWall  implements Serializable {
    private String resourceId;
    private String title;
    private String cover_type;
    private String cover_path;
    private String resource_path;
    private String resource_width;
    private String resource_height;
    private String resource_type;
    private String tag_id;
    private String visible_flag;
}
