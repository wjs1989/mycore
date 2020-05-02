package com.wjs.mybatis.model;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;


public class MyDataSource {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
