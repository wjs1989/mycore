package com.wjs.entity;

/**
 * @ClassName UserTemplate
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/26
 * @Version V1.0
 **/
public class UserTemplate {
    private String personName;

    public UserTemplate(String personName) {
        this.personName = personName;
    }


    public void doPrint(String str){
        System.out.println(personName+":"+str);
    }

}
