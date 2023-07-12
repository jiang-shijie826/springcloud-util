package com.jiang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.constant.RedisEnum;
import com.jiang.mapper.PhotoTagMapper;
import com.jiang.pojo.PhotoTag;
import com.jiang.service.PhotoTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PhotoTagServiceImpl  extends ServiceImpl<PhotoTagMapper, PhotoTag> implements PhotoTagService {

    @Resource
    private PhotoTagMapper photoTagMapper;

    @Resource
    private RedisTemplate<String, PhotoTag> redisTemplate;

    @Override
    public List<PhotoTag> queryAllTag() {
        //先从缓存中查询是否存在
        List<PhotoTag> tagList = redisTemplate.opsForList().range(RedisEnum.WALL_TAG.getValue(), 0, -1);
        log.info("缓存读取的数据为:{}", tagList);
        if (Objects.isNull(tagList) || tagList.size() == 0) {
            QueryWrapper<PhotoTag> queryWrapper  = new QueryWrapper<>();
            queryWrapper.orderByDesc("sort");
            List<PhotoTag> photoTagList = photoTagMapper.selectList(queryWrapper);
            if (photoTagList.size() > 0) {
                redisTemplate.opsForList().leftPushAll(RedisEnum.WALL_TAG.getValue(), photoTagList);
                tagList = new ArrayList<>(photoTagList.size());
                tagList.addAll(photoTagList);
            }
        }
        return tagList;
    }
}
