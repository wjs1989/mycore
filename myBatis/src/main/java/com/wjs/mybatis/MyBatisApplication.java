package com.wjs.mybatis;

import com.wjs.mybatis.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @ClassName MyBatisApplication
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
@EnableConfigurationProperties(ApplicationConfiguration.class)
@SpringBootApplication
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }

}
