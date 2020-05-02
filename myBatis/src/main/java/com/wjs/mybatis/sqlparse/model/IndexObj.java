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
public class IndexObj {

    private List<SelectList> selectList;
    private List<WeiduList> weiduList;
    public void setSelectList(List<SelectList> selectList) {
         this.selectList = selectList;
     }
     public List<SelectList> getSelectList() {
         return selectList;
     }

    public void setWeiduList(List<WeiduList> weiduList) {
         this.weiduList = weiduList;
     }
     public List<WeiduList> getWeiduList() {
         return weiduList;
     }

}