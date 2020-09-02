package com.wjs.multipleDataSource.slave.service;

import com.baomidou.mybatisplus.service.IService;
import com.wjs.multipleDataSource.master.entity.Sect;
import com.wjs.multipleDataSource.slave.entity.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author wjs
 * @Date 2020/7/27
 * @Version V1.0
 **/
public interface UserService extends IService<User> {
    List<User> getUsers();
}
