package com.wjs.mybatis.controller;

import com.sun.applet2.AppletParameters;
import com.wjs.mybatis.model.Animal;
import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 */
public class DbFactory {

    private Map<String,ObjectFactory<Animal>> manageCache = new HashMap<>();


    private void init() {
        manageCache.put("hive",() ->{return new Animal();});
        manageCache.put("persto",() ->{return new Animal();});
    }



    private Animal getManage(String key){
        ObjectFactory<Animal> animalObjectFactory = manageCache.get(key);
        Objects.requireNonNull(animalObjectFactory);

        return animalObjectFactory.getObject();
    }




}
