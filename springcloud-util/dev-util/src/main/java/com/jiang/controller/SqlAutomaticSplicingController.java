package com.jiang.controller;


import com.jiang.pojo.SqlAutomaticSplicing;
import com.jiang.service.SqlAutoSplService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc Sql工具
 */
@RestController
@RequestMapping("/sql")
public class SqlAutomaticSplicingController {

    @Resource
    private SqlAutoSplService sqlAutoSplService;

    /**
     * sql替换语句
     * @param sql /
     * @return /
     */
    @PostMapping("replace")
    public SqlAutomaticSplicing getReplaceSql(@RequestBody SqlAutomaticSplicing sql){
        return sqlAutoSplService.getReplaceSql(sql);
    }

}
