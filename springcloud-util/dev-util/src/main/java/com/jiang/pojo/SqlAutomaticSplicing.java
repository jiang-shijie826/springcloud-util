package com.jiang.pojo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author jiangsj
 * @create 2023/5/29
 * @desc Sql工具
 */
@Data
public class SqlAutomaticSplicing implements Serializable {

    private String sqlStatement;

    private String sqlPara;

    private String returnSql;

}
