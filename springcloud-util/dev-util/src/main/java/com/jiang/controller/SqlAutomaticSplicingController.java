package com.jiang.controller;


import com.jiang.pojo.SqlAutomaticSplicing;
import com.jiang.service.SqlAutoSplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    @PostMapping("replace")
    public SqlAutomaticSplicing getReplaceSql(@RequestBody SqlAutomaticSplicing sql){
        return sqlAutoSplService.getReplaceSql(sql);
    }

}
