package com.wjs.myProject.configuration;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/14 11:25
 */
@Configuration
public class RedissonConfig {


    @Bean
    public Redisson redisson(){
        Config config = new Config();

        config.useSingleServer().setAddress("10.204.125.208:6380").setPassword("redis-isky");
        //得到redisson对象
        return (Redisson) Redisson.create(config);

    }
}
