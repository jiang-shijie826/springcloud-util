package com.jiang.service;

import com.jiang.constant.Result;
import com.jiang.pojo.SqlAutomaticSplicing;
import org.springframework.stereotype.Component;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc Sql工具
 */
@Component
public interface SqlAutoSplService {

    /**
     * sql替换语句
     * @param sql /
     * @return /
     */
    Result<?> getReplaceSql(SqlAutomaticSplicing sql);

}
