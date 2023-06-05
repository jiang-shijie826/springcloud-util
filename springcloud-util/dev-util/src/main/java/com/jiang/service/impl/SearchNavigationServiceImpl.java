package com.jiang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.constant.RedisEnum;
import com.jiang.mapper.SearchNavigationMapper;
import com.jiang.pojo.SearchNavigation;
import com.jiang.service.SearchNavigationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 搜索导航
 */
@Slf4j
@Service
public class SearchNavigationServiceImpl implements SearchNavigationService {

    @Resource
    private SearchNavigationMapper searchNavigationMapper;

    @Resource
    private RedisTemplate<String, SearchNavigation> redisTemplate;

    /**
     * 查询所有地址
     *
     * @param count 地址展示数量
     * @return /
     */
    @Override
    public List<SearchNavigation> querySearchNavigation(int count) {
        List<SearchNavigation> searchList = redisTemplate.opsForList().range(RedisEnum.SEARCH_NAVIGATION.getValue(), 0, -1);
        if (Objects.isNull(searchList) || searchList.size() == 0) {
            QueryWrapper<SearchNavigation> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("id");
            wrapper.last("limit " + count);
            List<SearchNavigation> searchNavigations = searchNavigationMapper.selectList(wrapper);
            redisTemplate.opsForList().rightPushAll(RedisEnum.SEARCH_NAVIGATION.getValue(), searchNavigations);
            searchList = new ArrayList<>(searchNavigations.size());
            searchList.addAll(searchNavigations);
        }else {
            log.info("缓存读取的数据为:{}", searchList);
        }
        return searchList;
    }
}
