package com.jiang.controller;


import com.jiang.pojo.PhotoWall;
import com.jiang.service.PhotoWallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/wall")
public class PhotoWallController {

    @Resource
    private PhotoWallService photoWallService;

    @GetMapping("queryByTag/{tagId}")
    private List<PhotoWall> queryAllWallByTag(@PathVariable String tagId) {
        return photoWallService.queryAllWallByTag(tagId);
    }
}
