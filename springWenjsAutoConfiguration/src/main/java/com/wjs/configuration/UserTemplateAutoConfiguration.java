package com.wjs.configuration;

import com.wjs.entity.UserTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserTemplateAutoConfiguration
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/26
 * @Version V1.0
 **/
@Configuration
@ConditionalOnClass(UserTemplate.class)
@EnableConfigurationProperties(UserConfig.class)
public class UserTemplateAutoConfiguration {

    @Bean
    public UserTemplate userTemplate(UserConfig userConfig){
        UserTemplate userTemplate = new UserTemplate(userConfig.getPersonName());
        return userTemplate;
    }


}
