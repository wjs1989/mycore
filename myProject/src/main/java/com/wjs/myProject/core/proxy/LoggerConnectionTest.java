package com.wjs.myProject.core.proxy;

import com.wjs.myProject.core.proxy.impl.DefaultConnection;
import org.junit.Test;

/**
 * @ClassName LoggerConnectionTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class LoggerConnectionTest {

    @Test
    public void ConnectionTest(){
        IConnection connection = LoggerConnection.newInstance(new DefaultConnection());

        connection.doConnection("www.wjs.com");

    }
}
