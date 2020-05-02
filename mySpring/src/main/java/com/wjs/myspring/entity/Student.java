package com.wjs.myspring.entity;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName Studetn
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/12
 * @Version V1.0
 **/
public class Student{

    @Autowired
    private String userName ="我是一个学生";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
