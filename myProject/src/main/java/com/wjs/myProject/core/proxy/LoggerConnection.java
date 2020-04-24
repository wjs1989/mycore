package com.wjs.myProject.core.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName LoggerConnection
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/23
 * @Version V1.0
 **/
public class LoggerConnection implements InvocationHandler {
    private IConnection connection;

    private void begin(){
        System.out.print(" 我在做一些前置增强\r\n");
    }

    private void end(){
        System.out.print(" 我在做一些后置增强\r\n");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try{
             begin();
            method.invoke(connection,args);
            end();
        }catch (Exception e){

        }
        return null;
    }



    public static IConnection newInstance(IConnection connection){
        LoggerConnection loggerConnection = new LoggerConnection();
        loggerConnection.connection = connection;

        return (IConnection) Proxy.newProxyInstance(IConnection.class.getClassLoader(),new Class[]{IConnection.class},loggerConnection);
    }
}
