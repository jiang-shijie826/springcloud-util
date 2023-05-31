package com.jiang.controller;

import com.jiang.pojo.CarouselPicture;
import com.jiang.service.CarouselPictureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 图片轮播
 */
@RestController
@RequestMapping("/carousel")
public class CarouselPictureController {

    @Resource
    private CarouselPictureService pictureService;

    /**
     * 查询所有轮播图片
     * @param count 轮播图片数量
     * @return /
     */
    @ResponseBody
    @GetMapping("queryPicture/{count}")
    public List<CarouselPicture> queryPicture(@PathVariable int count){
        return pictureService.queryPicture(count);
    }

}
