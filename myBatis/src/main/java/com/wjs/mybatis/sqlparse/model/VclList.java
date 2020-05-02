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
public class VclList {

    private List<Children> children;
    private Long id;
    private String tablename;

    public void setChildren(List<Children> children) {
         this.children = children;
     }
     public List<Children> getChildren() {
         return children;
     }

    public void setId(Long id) {
         this.id = id;
     }
     public Long getId() {
         return id;
     }

    public void setTablename(String tablename) {
         this.tablename = tablename;
     }
     public String getTablename() {
         return tablename;
     }

}