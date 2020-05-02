package com.wjs.mybatis.sqlparse;

import com.wjs.mybatis.sqlparse.model.*;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName AbstractSqlParse
 * @Description: TODO 定义解析流程
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public abstract class AbstractSqlParse implements ISqlParse {
    private String selectColumn = "";
    private String mainFrom = "";
    private String joinSql = "";
    private String whereSql = "";

    /**
     * 别名
     */
    private List<String> aliasList=null;
    private int aliasIndex = 0;

    private Map<String,String> aliasCache ;

    @Override
    public String parse(GenericSqlModel genericSqlModel) throws Exception {
        if(genericSqlModel == null){
            return "";
        }

        init();

        parseMain(genericSqlModel);

        parseSelect(genericSqlModel.getIndexObj());

        parseWhere(genericSqlModel.getSizerObj());

        return buildSql();
    }
    private void init(){
        String str =  "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,b,q";
        aliasList= new ArrayList(Arrays.asList(str.split(",")));
        aliasCache = new HashMap<>();
    }

    /**
     * 获取表对应的别名
     * @param tableName
     * @return
     */
    protected String getAlias(String tableName){
        String alias = this.aliasCache.get(tableName);
        if(StringUtils.isEmpty(alias)){
            alias = this.aliasList.remove(0);
            if(StringUtils.isEmpty(alias)){
                alias = getAlias(tableName);
            }

            this.aliasCache.put(tableName,alias);
        }

        return alias;
    }

    /**
     * 解析主节点
     */
    private void parseMain(GenericSqlModel genericSqlModel) throws Exception {
        //主表查询只有一个，所以只要处理第一个节点
        if(genericSqlModel == null){
            throw new Exception("未获取到需要解析的数据");
        }
        List<VclList> vclList = genericSqlModel.getVclList();
        if(vclList == null && vclList.size() <= 0){
            throw new Exception("未获取到需要解析的数据");
        }

        VclList vclList1 = vclList.get(0);
        doParseMain(vclList1);

        List<Children> children = vclList1.getChildren();
        if(children!=null && children.size() > 0){
            parseChildren(children,vclList1.getTablename());
        }
    }
    /**
     * 解析子节点
     */
    private  void parseChildren(List<Children> children ,String parentTableName){
        if(children == null){
            return;
        }
        for(Children child : children){

            doParseChildren(child,parentTableName);

            //处理子节点
            List<Children> childrenNode = child.getChildren();
            if(childrenNode != null && childrenNode.size() > 0){
                parseChildren(childrenNode,child.getTablename());
            }
        }
    }

    private  void parseSelect(IndexObj indexObj){
        doParseSelect(indexObj);
    }

    private void parseWhere(SizerObj sizerObj){
        doParseWhere(sizerObj);
    }
    protected void appendSelectColumn(String column){
        if(StringUtils.isEmpty(column)){
            return;
        }

        this.selectColumn +=  column.trim() + ", ";
    }

    protected void appendMainTable(String tableName){
        if(StringUtils.isEmpty(tableName)){
            return;
        }

        this.mainFrom = tableName.trim();
    }

    protected void appendJoinSql(String joinSql){
        if(StringUtils.isEmpty(joinSql)){
            return;
        }

        this.joinSql += joinSql.trim() + " ";
    }

    protected void appendWhereSql(String where){
        if(StringUtils.isEmpty(where)){
            return;
        }

        where = where.replace("and","")
                    .replace("AND","")
                    .replace("WHERE","")
                    .replace("where","") ;
        where = where.trim();

        if(StringUtils.isEmpty(this.whereSql) ){
            where = String.format("where %s",where);
        }else{
            where = String.format("and %s",where);
        }

        this.whereSql += where + " ";
    }


    protected String buildSql(){
        String column =this.selectColumn;

        if(StringUtils.isEmpty(column)){
            column = "*";
        }

        column = column.trim();
        if(column.endsWith(",")){
            column = column.substring(0,column.length()-1);
        }

        String candidateSql = String.format(" select %s from %s %s %s ",
                column,
                this.mainFrom,
                this.joinSql,
                this.whereSql
        );

        return candidateSql;
    }

    protected  void doParseMain(VclList vclList){
        //继承类处理
    }
    protected  void doParseChildren(Children child,String parentTableName){
        //继承类处理
    }
    protected  void doParseSelect(IndexObj indexObj){
        //继承类处理
    }
    protected void doParseWhere(SizerObj sizerObj){
        //继承类处理
    }
}
