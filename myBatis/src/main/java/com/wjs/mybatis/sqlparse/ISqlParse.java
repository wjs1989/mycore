package com.wjs.mybatis.sqlparse;

import com.wjs.mybatis.sqlparse.model.GenericSqlModel;

/**
 * @ClassName ISqlParse
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public interface ISqlParse {

     String parse(GenericSqlModel genericSqlModel) throws Exception;

}
