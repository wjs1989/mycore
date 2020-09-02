package com.wjs.multipleDataSource.slave.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wjs.multipleDataSource.master.entity.Sect;
import com.wjs.multipleDataSource.slave.entity.User;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description: TODO
 * @Author wjs
 * @Date 2020/7/27
 * @Version V1.0
 **/
public interface UserMapper  extends BaseMapper<User> {

      List<User> getUsers();
}
