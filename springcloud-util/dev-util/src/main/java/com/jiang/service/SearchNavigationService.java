package com.jiang.service;

import com.jiang.pojo.SearchNavigation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 搜索导航
 */
@Component
public interface SearchNavigationService {

    /**
     * 查询所有地址
     * @param count 地址展示数量
     * @return /
     */
    List<SearchNavigation> querySearchNavigation(int count);
}
