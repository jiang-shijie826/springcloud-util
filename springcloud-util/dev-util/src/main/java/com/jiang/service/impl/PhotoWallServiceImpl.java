package com.jiang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.mapper.PhotoWallMapper;
import com.jiang.pojo.PhotoWall;
import com.jiang.service.PhotoWallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhotoWallServiceImpl extends ServiceImpl<PhotoWallMapper, PhotoWall> implements PhotoWallService {

    @Resource

    private PhotoWallMapper photoWallMapper;

    @Override
    public List<PhotoWall> queryAllWallByTag(int tagId) {
        QueryWrapper<PhotoWall> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id", tagId);
        return photoWallMapper.selectList(wrapper);
    }
}
