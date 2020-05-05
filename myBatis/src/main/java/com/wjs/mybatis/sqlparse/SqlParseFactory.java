package com.wjs.mybatis.sqlparse;

import org.mybatis.spring.SqlSessionTemplate;

/**
 * @ClassName SqlParseFactory
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class SqlParseFactory {
    public static <T extends ISqlParse> T createParser(Class<T> classT){

        try {

            return classT.newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }
}
