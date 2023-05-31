package com.jiang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.constant.RedisEnum;
import com.jiang.mapper.CarouselPictureMapper;
import com.jiang.pojo.CarouselPicture;
import com.jiang.service.CarouselPictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 图片轮播
 */
@Service
public class CarouselPictureServiceImpl implements CarouselPictureService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(CarouselPictureServiceImpl.class);

    @Resource
    private CarouselPictureMapper pictureMapper;

    @Resource
    private RedisTemplate<String, CarouselPicture> redisTemplate;

    @Override
    public List<CarouselPicture> queryPicture(int count) {
        //先从缓存中查询是否存在
        List<CarouselPicture> picList = redisTemplate.opsForList().range(RedisEnum.CAROUSEL_PICTURE.getValue(), 0, -1);
        LOGGER.info("缓存读取的数据为:{}", picList);
        if (Objects.isNull(picList) || picList.size() == 0) {
            QueryWrapper<CarouselPicture> wrapper = new QueryWrapper<>();
            wrapper.last("limit " + count);
            List<CarouselPicture> carouselPictures = pictureMapper.selectList(wrapper);
            redisTemplate.opsForList().leftPushAll(RedisEnum.CAROUSEL_PICTURE.getValue(), carouselPictures);
            picList = new ArrayList<>(carouselPictures.size());
            picList.addAll(carouselPictures);
        }
        return picList;
    }
}
