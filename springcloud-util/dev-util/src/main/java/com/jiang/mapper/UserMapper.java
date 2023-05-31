package com.jiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 77628
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
