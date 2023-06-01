package com.jiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc 用户管理
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
