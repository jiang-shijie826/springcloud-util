package com.jiang.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 搜索导航
 */
@Data
public class SearchNavigation  implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String text;
    private String src;
    private String img;
}
