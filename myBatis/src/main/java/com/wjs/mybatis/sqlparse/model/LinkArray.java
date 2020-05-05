/**
  * Copyright 2020 bejson.com 
  */
package com.wjs.mybatis.sqlparse.model;
/**
 * @ClassName GenericSqlModel
 * @Description: TODO 封装sql原型数据
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class LinkArray {

    private String leftcolumn;
    private String rightcolumn;
    private String symbol;

    public void setLeftcolumn(String leftcolumn) {
         this.leftcolumn = leftcolumn;
     }
     public String getLeftcolumn() {
         return leftcolumn;
     }

    public void setRightcolumn(String rightcolumn) {
         this.rightcolumn = rightcolumn;
     }
     public String getRightcolumn() {
         return rightcolumn;
     }

    public void setSymbol(String symbol) {
         this.symbol = symbol;
     }
     public String getSymbol() {
         return symbol;
     }

}