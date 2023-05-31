package com.jiang.controller;

import com.jiang.pojo.SearchNavigation;
import com.jiang.service.SearchNavigationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 搜索导航
 */
@RestController
@RequestMapping("/search")
public class SearchNavigationController {

    @Resource
    private SearchNavigationService searchNavigationService;

    @ResponseBody
    @GetMapping("query/{count}")
    public List<SearchNavigation> querySearchNavigation(@PathVariable int count){
        return searchNavigationService.querySearchNavigation(count);
    }

}
