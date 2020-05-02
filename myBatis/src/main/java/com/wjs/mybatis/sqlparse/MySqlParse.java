package com.wjs.mybatis.sqlparse;

import com.wjs.mybatis.sqlparse.model.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName MySqlParse
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class MySqlParse extends AbstractSqlParse {
    final String AS = "as";
    final String DEFAULT_JOIN = "join";
    final String JOIN_TYPE="inner,cross,left,right,outer";
    final String ON = "on";
    final String AND = "and";
    
    /**
     * 解析select
     * @param indexObj
     */
    @Override
    protected  void doParseSelect(IndexObj indexObj){
        if(indexObj == null){
            return;
        }

        List<SelectList> selectList = indexObj.getSelectList();
        if(selectList == null){
            return;
        }

        List<WeiduList> weiduList = indexObj.getWeiduList();

        //循环解析column
        int parseIndex = 0;
        for(SelectList select:selectList){
            String column = select.getColumn();
            if(StringUtils.isEmpty(column)){
                parseIndex++;
                continue;
            }
            //查看列名称是否是table.column 格式
            String[] columns = column.split("\\.");
            //有多个. 则不处理
            if(columns.length >2){
                parseIndex++;
                continue;
            } else if(columns.length == 2){
                String tableName = columns[0];
                column =  columns[1];
                if(!StringUtils.isEmpty(tableName)){
                    String alias = getAlias(tableName);
                    column =String.format("%s.%s",alias,columns[1]);
                }
            }

            // 处理聚合函数
            String varia = select.getVaria();
            if(!StringUtils.isEmpty(varia)){
                column = String.format("%s(%s)",varia,column);
            }

            if(weiduList!= null && weiduList.size() > parseIndex){
                WeiduList weiduModel = weiduList.get(parseIndex);
                if(weiduModel != null){
                    String weidu = weiduModel.getWeidu();

                    if(!StringUtils.isEmpty(weidu)){
                        column = String.format("%s %s %s",column,AS,weidu);
                    }
                }

            }

            appendSelectColumn(column);
            parseIndex++;
        }

    }

    /**
     * 解析主表 即 from 后面的 表
     * @param vclList
     */
    @Override
    protected  void doParseMain(VclList vclList){
        if(vclList == null){
            return ;
        }

        String tableName = vclList.getTablename();

        if (StringUtils.isEmpty(tableName)) {
            return ;
        }

        String alias = getAlias(tableName);
        tableName = String.format("%s %s %s",tableName,AS,alias);

        appendMainTable(tableName);
    }

    /**
     * 处理子节点 join结构
     * @param child
     */
    @Override
    protected  void doParseChildren(Children child,String parentTableName){
        if(child == null || StringUtils.isEmpty(parentTableName) ||
            StringUtils.isEmpty(child.getTablename())
        ){
            return ;
        }

        LinkObj linkObj = child.getLinkObj();
        if(linkObj == null){
            return ;
        }

        String checkstyle = linkObj.getCheckstyle();
        String CandidateCheckstyle = DEFAULT_JOIN;
        if(StringUtils.isEmpty(checkstyle)){
            checkstyle = DEFAULT_JOIN;
        }else{ //检测常用join方式
            String firstKey = "";
            String secondKey = "";
            checkstyle = checkstyle.trim().toLowerCase();

            // 检测是否 "left join" 这种格式
            String[] keys = checkstyle.split(" ");
            if(keys.length == 2){
                if(!JOIN_TYPE.contains(keys[0])){
                    //前缀不对 则不处理
                    return;
                }

                // 第二个参数是否为 join
                if(!DEFAULT_JOIN.equals(keys[1])){
                    //不是join 则不处理
                    return;
                }
            }else if(keys.length == 1){
                //只有 一个参数，检测是否为前缀 或者 join
                if(JOIN_TYPE.contains(keys[0])){
                    checkstyle += " " + DEFAULT_JOIN;
                }else if(!DEFAULT_JOIN.equals(keys[0])){
                    //格式不对 则不处理
                    return;
                }
            }else{
                //不对的格式
                return;
            }
        }

        // 解析关联条件
        List<LinkArray> linkArray = linkObj.getLinkArray();
        if(linkArray == null && linkArray.size() <= 0){
            //没有条件，不处理
            return;
        }

        final String tableName = child.getTablename();
        final String childAlias = getAlias(tableName);
        final String parentAlias = getAlias(parentTableName);

        //默认左条件为父节点 ，右条件为子节点
        String joinDefinitation = "";
        for(LinkArray link : linkArray){
            String leftcolumn = link.getLeftcolumn();
            String rightcolumn = link.getRightcolumn();
            String symbol = link.getSymbol();
            if(StringUtils.isEmpty(leftcolumn) || StringUtils.isEmpty(rightcolumn) || StringUtils.isEmpty(symbol)){
                //条件不全，不处理
                continue;
            }

            String operator = ON;
            if(!StringUtils.isEmpty(joinDefinitation)){
                operator = AND;
            }
            joinDefinitation += String.format("%s %s.%s %s %s.%s ",
                    operator,parentAlias,leftcolumn,symbol,childAlias,rightcolumn);
        }

        //解析完成，添加到joinSql缓存
        joinDefinitation = String.format("%s %s %s %s %s ",
                checkstyle,tableName,AS,childAlias,joinDefinitation);
        appendJoinSql(joinDefinitation);
    }

    /**
     * 解析where
     * @param sizerObj
     */
    @Override
    protected void doParseWhere(SizerObj sizerObj){


        // 支持以下格式
        //appendWhereSql("where 1=1");
        //appendWhereSql("1=2");
    }
}
