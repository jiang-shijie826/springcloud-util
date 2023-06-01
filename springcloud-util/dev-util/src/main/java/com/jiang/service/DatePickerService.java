package com.jiang.service;

import com.jiang.pojo.BingPicture;
import org.springframework.stereotype.Component;

/**
 * @author jiangsj
 * @create 2023/6/1
 * @desc
 */
@Component
public interface DatePickerService {

    /**
     * 获取必应每日壁纸
     * @return /
     */
    BingPicture queryBing();
}
