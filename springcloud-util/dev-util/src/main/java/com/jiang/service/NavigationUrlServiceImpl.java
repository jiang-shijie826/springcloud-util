package com.jiang.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiang.constant.RedisEnum;
import com.jiang.mapper.NavigationUrlMapper;
import com.jiang.pojo.NavigationUrl;
import com.jiang.service.NavigationUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 导航地址
 */
@Slf4j
@Service
public class NavigationUrlServiceImpl implements NavigationUrlService {
    @Resource
    private NavigationUrlMapper navigationUrlMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Object> queryNavigationUrl() {
        List<Object> navigationList = redisTemplate.opsForList().range(RedisEnum.NAVIGATION_URL.getValue(), 0, -1);
        log.info("缓存读取的数据为:{}", navigationList);
        if (Objects.isNull(navigationList) || navigationList.size() == 0) {
            //数据库中查询数据
            List<NavigationUrl> navigationUrlList = navigationUrlMapper.selectList(null);
            //根据tag进行分组处理
            Map<String, List<NavigationUrl>> listMap = navigationUrlList.stream().collect(Collectors.groupingBy(NavigationUrl::getTag));
            //将分组结果进行处理,并缓存
            navigationList = new ArrayList<>(navigationUrlList.size());
            for (Map.Entry<String, List<NavigationUrl>> entry : listMap.entrySet()) {
                Map<String, Object> map = new HashMap<>(2);
                map.put("tag", entry.getKey());
                map.put("list", entry.getValue());
                navigationList.add(map);
            }
            redisTemplate.opsForList().leftPushAll(RedisEnum.NAVIGATION_URL.getValue(), navigationList);
        }
        return navigationList;
    }

    /**
     * 更新浏览次数
     * @param navigationUrl /
     */
    @Override
    public void updateNavigationCount(NavigationUrl navigationUrl) {
        //查询缓存中
        Set<Object> commonAddressesList = redisTemplate.opsForZSet().range(RedisEnum.COMMON_ADDRESSES.getValue(), 0, -1);
        //如果缓存中不存在就新增到缓存中
        navigationUrl.setScore(null);
        if (Objects.requireNonNull(commonAddressesList).contains(navigationUrl)) {
            //判断是否重复,重复更新count+1
            redisTemplate.opsForZSet().incrementScore(RedisEnum.COMMON_ADDRESSES.getValue(), navigationUrl, 1);
        }else {
            commonAddressesList.add(navigationUrl);
            redisTemplate.opsForZSet().add(RedisEnum.COMMON_ADDRESSES.getValue(), navigationUrl, 1);
        }
    }

    @Override
    public Set<Object> queryCommonUrl() {
        Set<Object> reverseRange = redisTemplate.opsForZSet().reverseRange(RedisEnum.COMMON_ADDRESSES.getValue(), 0, -1);
        LinkedHashSet<Object> finalSet = null;
        if (reverseRange != null) {
            finalSet = new LinkedHashSet<>(reverseRange.size());
            for (Object obj : reverseRange) {
                NavigationUrl url = new ObjectMapper().convertValue(obj, NavigationUrl.class);
                url.setScore(Objects.requireNonNull( redisTemplate.opsForZSet().score(RedisEnum.COMMON_ADDRESSES.getValue(), url)));
                finalSet.add(url);
            }
        }
        return  finalSet;
    }

}
