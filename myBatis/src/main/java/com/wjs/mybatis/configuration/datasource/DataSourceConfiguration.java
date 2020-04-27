package com.wjs.mybatis.configuration.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
public class DataSourceConfiguration {

   // @Bean
    public DynamicDataSource dynamicDataSource(){
        DataSource dataSource = new HikariDataSource() ;
        DynamicDataSource d = new DynamicDataSource();

        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("mysql1",dataSource);

        d.setDefaultTargetDataSource(dataSource);

        d.setTargetDataSources(dataSourceMap);
        return d;
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    DynamicDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }

        DynamicDataSource d = new DynamicDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("mysql1",dataSource);
        dataSourceMap.put("mysql2",dataSource);
        dataSourceMap.put("mysql3",dataSource);

        d.setDefaultTargetDataSource(dataSource);
        d.setTargetDataSources(dataSourceMap);
        return d;
    }

    protected <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }
}
