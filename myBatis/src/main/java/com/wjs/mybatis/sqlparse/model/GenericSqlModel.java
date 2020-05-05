package com.wjs.mybatis.sqlparse.model;

import java.util.List;

/**
 * @ClassName GenericSqlModel
 * @Description: TODO 封装sql原型数据
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class GenericSqlModel {

    private Long dbid;
    private IndexObj indexObj;
    private SizerObj sizerObj;
    private List<VclList> vclList;

    public List<VclList> getVclList() {
        return vclList;
    }

    public void setVclList(List<VclList> vclList) {
        this.vclList = vclList;
    }

    public Long getDbid() {
        return dbid;
    }

    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }

    public IndexObj getIndexObj() {
        return indexObj;
    }

    public void setIndexObj(IndexObj indexObj) {
        this.indexObj = indexObj;
    }

    public SizerObj getSizerObj() {
        return sizerObj;
    }

    public void setSizerObj(SizerObj sizerObj) {
        this.sizerObj = sizerObj;
    }
}
