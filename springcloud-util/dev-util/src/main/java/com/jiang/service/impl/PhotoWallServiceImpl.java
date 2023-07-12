package com.jiang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.constant.RedisEnum;
import com.jiang.mapper.PhotoWallMapper;
import com.jiang.pojo.PhotoWall;
import com.jiang.service.PhotoWallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 77628
 */
@Slf4j
@Service
public class PhotoWallServiceImpl extends ServiceImpl<PhotoWallMapper, PhotoWall> implements PhotoWallService {

    @Resource
    private PhotoWallMapper photoWallMapper;

    @Resource
    private RedisTemplate<String, PhotoWall> redisTemplate;

    @Override
    public List<PhotoWall> queryAllWallByTag(int tagId) {
        //先从缓存中查询是否存在
        List<PhotoWall> picList = redisTemplate.opsForList().range(RedisEnum.WALL_LIST.getValue() + tagId, 0, -1);
        log.info("缓存读取的数据为:{}", picList);
        if (Objects.isNull(picList) || picList.size() == 0) {
            QueryWrapper<PhotoWall> wrapper = new QueryWrapper<>();
            wrapper.eq("tag_id", tagId);
            List<PhotoWall> photoWallList = photoWallMapper.selectList(wrapper);
            if (photoWallList.size() > 0) {
                redisTemplate.opsForList().leftPushAll(RedisEnum.WALL_LIST.getValue() + tagId, photoWallList);
                picList = new ArrayList<>(photoWallList.size());
                picList.addAll(photoWallList);
            }
        }
        return picList;
    }
}
