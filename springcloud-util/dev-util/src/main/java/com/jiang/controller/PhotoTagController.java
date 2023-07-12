package com.jiang.controller;


import com.jiang.constant.Result;
import com.jiang.service.PhotoTagService;
import com.jiang.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wallTag")
public class PhotoTagController {

    @Resource
    private PhotoTagService photoTagService;

    @GetMapping("queryAllTag")
    private Result<?> queryAllTag() {
        return ResultUtil.successData(photoTagService.queryAllTag());
    }

}
