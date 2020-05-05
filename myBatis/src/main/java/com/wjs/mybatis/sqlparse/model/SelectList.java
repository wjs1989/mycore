/**
  * Copyright 2020 bejson.com 
  */
package com.wjs.mybatis.sqlparse.model;

public class SelectList {

    private String column;
    private String symbol;
    private String val;
    private String varia;


    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getVaria() {
        return varia;
    }

    public void setVaria(String varia) {
        this.varia = varia;
    }
}