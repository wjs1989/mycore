package com.wjs.mybatis.configuration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return "mysql4";
    }


    private Map<Object, Object> targetDataSourcesSub;

    public void setTargetDataSources(Map<Object, Object> targetDataSources){
        super.setTargetDataSources(targetDataSources);
        if(this.targetDataSourcesSub != null){
            super.afterPropertiesSet();
        }
        this.targetDataSourcesSub = targetDataSources;

    }

    public Map<Object, Object> getTargetDataSources(){
        return this.targetDataSourcesSub;
    }
}
