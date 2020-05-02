package com.wjs.mybatis.configuration;

import com.wjs.mybatis.plugin.SqlInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName MyBatisInterceotorsConfiguration
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/24
 * @Version V1.0
 **/
@Configuration
public class MyBatisInterceotorsConfiguration {

    @Bean
    public SqlInterceptor sqlInterceptor() {

        //插件拦截链采用了责任链模式，执行顺序和加入连接链的顺序有关
        SqlInterceptor sqlInterceptor = new SqlInterceptor();
        //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
        Properties properties = new Properties();
        //这里设置慢查询阈值为1毫秒，便于测试
        properties.setProperty("time", "1");
        sqlInterceptor.setProperties(properties);

        return sqlInterceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){

            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
                configuration.addInterceptor(sqlInterceptor());
            }
        };
//      return configuration->{
//            configuration.setMapUnderscoreToCamelCase(true);
//            configuration.addInterceptor(sqlInterceptor());
//        };
    }

}
