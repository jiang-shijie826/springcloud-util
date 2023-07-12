package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoWall  implements Serializable {
    private String resourceId;
    private String title;
    private int coverType;
    private String coverPath;
    private String resourcePath;
    private String resourceWidth;
    private String resourceHeight;
    private String resourceType;
    private String tagId;
    private int visibleFlag;
}
