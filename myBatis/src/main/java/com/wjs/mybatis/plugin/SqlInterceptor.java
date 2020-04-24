package com.wjs.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * @ClassName SsqlInterceptor
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/24
 * @Version V1.0
 **/
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class, Integer.class})
})
public class SqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        Object proceed = invocation.proceed();

        //Object[] args = invocation.getArgs();
        //Statement statement = (Statement)args[0];

       // MetaObject metaObject = SystemMetaObject.forObject(statement);
       // PreparedStatementLogger h = (PreparedStatementLogger) metaObject.getValue("h");

       // Statement preparedStatement = h.getPreparedStatement();

        System.out.println(sql);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
