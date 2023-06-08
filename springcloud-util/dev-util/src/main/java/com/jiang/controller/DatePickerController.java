package com.jiang.controller;

import com.jiang.pojo.BingPicture;
import com.jiang.service.DatePickerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiangsj
 * @create 2023/6/1
 * @desc
 */
@RestController
@RequestMapping("/datePicker")
public class DatePickerController {

    @Resource
    private DatePickerService datePickerService;

    /**
     * 获取必应每日壁纸
     * @return /
     */
    @GetMapping("queryBing")
    public BingPicture queryBing(){
        return datePickerService.queryBing();
    }
}
