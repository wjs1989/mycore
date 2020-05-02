package com.wjs.mybatis.Service.impl;

import com.wjs.mybatis.Service.IPersonService;
import com.wjs.mybatis.dao.PersonMapper;
import com.wjs.mybatis.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PersonServiceImpl
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
@Service
public class PersonServiceImpl implements IPersonService {

    //@Autowired
    private PersonMapper personMapper;

    @Override
    public int insert(Person record) {
        return personMapper.insert(record);
    }

    @Transactional
    @Override
    public Person selectByPrimaryKey(String id) {
        return personMapper.selectByPrimaryKey(id);
    }
}
