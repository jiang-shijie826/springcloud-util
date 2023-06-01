package com.jiang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiang.constant.RedisEnum;
import com.jiang.pojo.BingPicture;
import com.jiang.service.DatePickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author jiangsj
 * @create 2023/6/1
 * @desc
 */
@Service
public class DatePickerServiceImpl implements DatePickerService {
    private static final Logger LOGGER  = LoggerFactory.getLogger(DatePickerServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public BingPicture queryBing() {
        BingPicture bingPicture = new BingPicture();
        //从缓存中查询是否能获取到当天的壁纸
        Map<Object, Object> bingPic = redisTemplate.opsForHash().entries(String.valueOf(RedisEnum.BING_PICTURE.getValue()));
        if (Objects.isNull(bingPic.get(RedisEnum.STARTDATE.getValue())) || !bingPic.get(RedisEnum.STARTDATE.getValue()).equals(getDate())) {
            bingPicture = requestForBing();
        }else {
            bingPicture.setStartDate(bingPic.get(RedisEnum.STARTDATE.getValue()).toString());
            bingPicture.setUrl(bingPic.get(RedisEnum.URL.getValue()).toString());
            LOGGER.info("缓存读取的壁纸为:{}", bingPicture);
        }
        return bingPicture;
    }

    /**
     * 获取前一天的额日期
     * @return /
     */
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return format.format(calendar.getTime());
    }

    /**
     * 请求bing每日壁纸 api
     * @return /
     */
    private BingPicture requestForBing() {
        JSONObject jsonObj = JSON.parseObject(
                restTemplate.getForEntity("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1",
                        String.class).getBody()
        );
        List<Object> images = (List<Object>) jsonObj.get("images");
        BingPicture bingPicture = new BingPicture();
        if (!images.isEmpty()) {
            JSONObject img = JSON.parseObject(images.get(0).toString());
            bingPicture.setStartDate(img.get("startdate").toString());
            bingPicture.setUrl("https://cn.bing.com" + img.get("url").toString());
        }
        redisTemplate.opsForHash().put(RedisEnum.BING_PICTURE.getValue(), RedisEnum.STARTDATE.getValue(), bingPicture.getStartDate());
        redisTemplate.opsForHash().put(RedisEnum.BING_PICTURE.getValue(), RedisEnum.URL.getValue(), bingPicture.getUrl());
        return  bingPicture;
    }



}
