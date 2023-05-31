package com.jiang.service.impl;

import com.jiang.pojo.SqlAutomaticSplicing;
import com.jiang.service.SqlAutoSplService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc Sql工具
 */
@Service
public class SqlAutoSplServiceImpl implements SqlAutoSplService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(SqlAutoSplServiceImpl.class);

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
        LOGGER.info("替换的SQL语句为:{}", sqlStatement);
        return sql;
    }
}
