package com.wjs.multipleDataSource.config;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
/**
 * @ClassName SlaveDataSourceConfig
 * @Description: TODO
 * @Author wjs
 * @Date 2020/7/27
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.wjs.multipleDataSource.slave.mapper",sqlSessionFactoryRef = "slaveSqlSessionFactory",sqlSessionTemplateRef="slaveSqlSessionTemplate")
public class SlaveDataSourceConfig {

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean ();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml") );
// sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/*/*Mapper.xml"));

        return sessionFactoryBean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

        return sqlSessionTemplate;
    }

}
