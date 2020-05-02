package com.wjs.myspring.entity;

import com.wjs.myProject.core.annotation.LoadBalanceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wenjs {

    @LoadBalanceRest
    @Autowired
    private Person person;
}
