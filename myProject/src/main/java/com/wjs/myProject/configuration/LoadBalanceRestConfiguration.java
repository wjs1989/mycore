package com.wjs.myProject.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoadBalanceRestConfiguration {

    @Value("${spring.wjs.person-name}")
    private String name;

    public String getName(){
        return name;
    }
}
