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
public class LinkObj {

    private String checkstyle;
    private List<LinkArray> linkArray;

    public void setCheckstyle(String checkstyle) {
         this.checkstyle = checkstyle;
     }
     public String getCheckstyle() {
         return checkstyle;
     }

    public void setLinkArray(List<LinkArray> linkArray) {
         this.linkArray = linkArray;
     }
     public List<LinkArray> getLinkArray() {
         return linkArray;
     }

}