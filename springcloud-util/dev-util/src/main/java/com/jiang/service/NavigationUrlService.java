package com.jiang.service;

import com.jiang.pojo.NavigationUrl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 导航地址
 */
@Component
public interface NavigationUrlService {

    /**
     * 查询所有导航地址
     *
     * @return /
     */
    List<Object> queryNavigationUrl();

    /**
     * 更新浏览次数
     * @param navigationUrl /
     * @return /
     */
    void updateNavigationCount(NavigationUrl navigationUrl);

    /**
     * 查询redis中常用地址
     * @return /
     */
    Set<Object> queryCommonUrl();

}
