package com.jiang.service.impl;

import com.jiang.constant.Result;
import com.jiang.pojo.SqlAutomaticSplicing;
import com.jiang.service.SqlAutoSplService;
import com.jiang.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc Sql工具
 */
@Slf4j
@Service
public class SqlAutoSplServiceImpl implements SqlAutoSplService {

    /**
     * sql替换语句
     *
     * @param sql /
     * @return /
     */
    @Override
    public Result<?> getReplaceSql(SqlAutomaticSplicing sql) {
        //替换sql
        String sqlStatement = sql.getSqlStatement();
        String sqlPara =  StringUtils.deleteWhitespace(sql.getSqlPara());
        String[] paraSplit = sqlPara.split(",");
        //判断参数是否正确
        int count = 0;
        char[] chars = sqlStatement.toCharArray();
        for (char str : chars) {
            if (str == '?') {
                count++;
            }
        }
        if (count != paraSplit.length) {
            return ResultUtil.failureMsg("SQL参数有误,请检查!");
        }
        for (String s : paraSplit) {
            String[] split = s.split("\\(");
            String type = split[1].replace(")", "");
            if ("String".equals(type) || "Timestamp".equals(type) || "".equals(type)) {
                split[0] = "'" + split[0] + "'";
            }
            sqlStatement = sqlStatement.replaceFirst("\\?", split[0]);
        }
        sql.setReturnSql(sqlStatement);
        log.info("替换的SQL语句为:{}", sqlStatement);
        return ResultUtil.successData(sql);
    }
}
