package com.jiang.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.pojo.PhotoTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoTagService  extends IService<PhotoTag> {
    List<PhotoTag> queryAllTag();
}
