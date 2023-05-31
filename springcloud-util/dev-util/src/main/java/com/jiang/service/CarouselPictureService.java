package com.jiang.service;

import com.jiang.pojo.CarouselPicture;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 图片轮播
 */
@Component
public interface CarouselPictureService {

    /**
     * 查询所有轮播图片
     * @param count 轮播图片数量
     * @return /
     */
    List<CarouselPicture> queryPicture(int count);

}
