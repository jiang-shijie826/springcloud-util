package com.jiang.controller;

import com.jiang.pojo.NavigationUrl;
import com.jiang.service.NavigationUrlService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 导航地址
 */
@RestController
@RequestMapping("/navigation")
public class NavigationUrlController {

    @Resource
    private NavigationUrlService navigationUrlService;

    /**
     * 查询所有导航地址
     * @return /
     */
    @ResponseBody
    @GetMapping("queryAll")
    public List<Object> queryAllNavigationUrl() {
        return navigationUrlService.queryNavigationUrl();
    }

    /**
     * 更新浏览次数
     */
    @ResponseBody
    @PostMapping("updateCount")
    public void updateNavigationCount(@RequestBody NavigationUrl navigationUrl) {
        navigationUrlService.updateNavigationCount(navigationUrl);
    }

    @ResponseBody
    @GetMapping("queryCommon")
    public Set<Object> queryCommonUrl() {
        return navigationUrlService.queryCommonUrl();
    }

}
