package com.wjs.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName UserConfig
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/26
 * @Version V1.0
 **/
@ConfigurationProperties(prefix="spring.wjs")
public class UserConfig {

    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
