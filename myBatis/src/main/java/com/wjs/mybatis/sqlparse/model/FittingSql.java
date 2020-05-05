/**
  * Copyright 2020 bejson.com 
  */
package com.wjs.mybatis.sqlparse.model;
import java.util.List;

/**
 * Auto-generated: 2020-04-17 19:36:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FittingSql {

    private Long dbid;
    private IndexObj indexObj;
    private SizerObj sizerObj;
    private List<VclList> vclList;
    public void setDbid(Long dbid) {
         this.dbid = dbid;
     }
     public Long getDbid() {
         return dbid;
     }

    public void setIndexObj(IndexObj indexObj) {
         this.indexObj = indexObj;
     }
     public IndexObj getIndexObj() {
         return indexObj;
     }

    public void setSizerObj(SizerObj sizerObj) {
         this.sizerObj = sizerObj;
     }
     public SizerObj getSizerObj() {
         return sizerObj;
     }

    public void setVclList(List<VclList> vclList) {
         this.vclList = vclList;
     }
     public List<VclList> getVclList() {
         return vclList;
     }

}