package com.wjs.mybatis.dao;

import com.wjs.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByPrimaryKey(Integer id);
}