package com.wjs.mybatis.Service;

import com.wjs.mybatis.pojo.Person;

/**
 * @ClassName IPersonService
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
public interface IPersonService {

    int insert(Person record);


    Person selectByPrimaryKey(String id);
}
