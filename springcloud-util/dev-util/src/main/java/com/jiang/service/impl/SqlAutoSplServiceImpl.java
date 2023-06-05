package com.jiang.service.impl;

import com.jiang.pojo.SqlAutomaticSplicing;
import com.jiang.service.SqlAutoSplService;
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
    public SqlAutomaticSplicing getReplaceSql(SqlAutomaticSplicing sql) {
        //替换sql
        String sqlStatement = sql.getSqlStatement();
        String sqlPara =  StringUtils.deleteWhitespace(sql.getSqlPara());
        String[] paraSplit = sqlPara.split(",");
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
        return sql;
    }
}
