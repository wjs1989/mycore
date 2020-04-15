package com.wjs.myspring.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {

    private String name;
    private int age;

    @Autowired
    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
