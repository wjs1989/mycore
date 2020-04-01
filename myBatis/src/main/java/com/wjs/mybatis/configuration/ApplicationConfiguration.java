package com.wjs.mybatis.configuration;

import com.wjs.mybatis.pojo.Person;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName ApplicationConfiguration
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/25
 * @Version V1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource",ignoreInvalidFields = true)
@PropertySource("classpath:/application.yml")
public class ApplicationConfiguration {

    private String username;
    private String password;
    private String  url;
    private String driveClassName;


    @Bean
    public Person person(){
        Person person = new Person();
        person.setName(username);
        return person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriveClassName() {
        return driveClassName;
    }

    public void setDriveClassName(String driveClassName) {
        this.driveClassName = driveClassName;
    }
}
