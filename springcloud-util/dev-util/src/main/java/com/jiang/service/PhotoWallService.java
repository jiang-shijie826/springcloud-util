package com.jiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.pojo.PhotoWall;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoWallService  extends IService<PhotoWall> {
    List<PhotoWall> queryAllWallByTag(int tagId);
}
