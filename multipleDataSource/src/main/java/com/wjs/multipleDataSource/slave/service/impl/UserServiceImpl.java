package com.wjs.multipleDataSource.slave.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wjs.multipleDataSource.slave.entity.User;
import com.wjs.multipleDataSource.slave.mapper.UserMapper;
import com.wjs.multipleDataSource.slave.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author wjs
 * @Date 2020/7/27
 * @Version V1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> getUsers() {
        return this.baseMapper.getUsers();
    }
}
