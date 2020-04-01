package com.wjs.mybatis;

import com.wjs.mybatis.pojo.Person;
import org.junit.Test;

import java.sql.*;

/**
 * @ClassName JdbcDemo
 * @Description: Jdbc 执行示例
 * @Author wjs
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class JdbcDemo {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "123456";


    @Test
    public void query() throws SQLException,Exception {
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Person person = null;

        try{
            // STEP 2: 注册mysql的驱动
            Class.forName(JDBC_DRIVER);

            // STEP 3: 获得一个连接
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 4: 创建一个查询/
            String sql = "select * from person where id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"3f6d45b2-db58-4132-b868-ba4b01ac5af1");
            resultSet = preparedStatement.executeQuery();

            //f返回
            while (resultSet.next()) {
                person = new Person();
                person.setName(resultSet.getString("name"));
            }
            System.out.println(person.getName());
        }  finally {
            if(resultSet != null)
                resultSet.close();

            if(connection != null)
                connection.close();

            if(preparedStatement != null)
                preparedStatement.close();
        }
    }


}
