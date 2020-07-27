package com.wjs.multipleDataSource.master.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wjs.multipleDataSource.master.entity.Sect;
import com.wjs.multipleDataSource.master.mapper.SectMapper;
import com.wjs.multipleDataSource.master.service.SectService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SectServiceImpl
 * @Description: TODO
 * @Author wjs
 * @Date 2020/7/27
 * @Version V1.0
 **/
@Service
public class SectServiceImpl extends ServiceImpl<SectMapper, Sect> implements SectService {
}
