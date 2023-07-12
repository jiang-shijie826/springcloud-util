package com.jiang.controller;


import com.jiang.constant.Result;
import com.jiang.service.PhotoWallService;
import com.jiang.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wall")
public class PhotoWallController {

    @Resource
    private PhotoWallService photoWallService;

    @GetMapping("queryByTag/{tagId}")
    private Result<?> queryAllWallByTag(@PathVariable int tagId) {
        return ResultUtil.successData(photoWallService.queryAllWallByTag(tagId));
    }



}
