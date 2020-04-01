package com.wjs.myProject.core.plugin;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @ClassName PluginTest2
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/24
 * @Version V1.0
 **/
public class PluginTest2 {

    @Test
    public void shouldNotInterceptToString() {
        InterceptorChain interceptorChain = new InterceptorChain();
        AlwaysMapPlugin alwaysMapPlugin =new AlwaysMapPlugin();
        Properties properties = new Properties();
        properties.setProperty("time","1");
        alwaysMapPlugin.setProperties(properties);

        interceptorChain.addInterceptor(alwaysMapPlugin);
        Map map = new HashMap();
        map = (Map)interceptorChain.pluginAll(map);
        System.out.println(map.get("wjs"));

    }




    @Intercepts({
            @Signature(type = Map.class, method = "get", args = {Object.class})})
    public class AlwaysMapPlugin implements Interceptor {
        private String time ;

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            Object proceed = invocation.proceed();
            if(proceed == null ){
                System.out.println("值为null");
            }

            return proceed;
        }

        @Override
        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        @Override
        public void setProperties(Properties properties) {
            time = properties.getProperty("time");
        }
    }
}
