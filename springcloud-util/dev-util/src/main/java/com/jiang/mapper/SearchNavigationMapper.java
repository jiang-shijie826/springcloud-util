package com.jiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.pojo.SearchNavigation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 搜索导航
 */
@Mapper
public interface SearchNavigationMapper  extends BaseMapper<SearchNavigation> {
}
