package com.wjs.mybatis.model;

/**
 * @ClassName Animal
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
public class Animal {
    private Animal parent;
    private String name;

    public Animal getParent() {
        return parent;
    }

    public void setParent(Animal parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
