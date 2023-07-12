package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoWall  implements Serializable {
    private String id;
    private String title;
<<<<<<< .mine
    private int coverType;
    private String coverPath;
    private String resourcePath;
    private String resourceWidth;
    private String resourceHeight;
    private String resourceType;
    private String tagId;
    private int visibleFlag;
=======
    private String img_url;
    private String tag_id;
    private String visible_flag;





>>>>>>> .theirs
}
