package cn.edu.zucc.ding.summerterm.util;

import java.sql.ResultSet;

public class DatabaseOp {
    public static String select(String element,String table,String condition){
        String sql = "Select " + element + " from " +table;
        if(!condition.equals("")){
            sql +=" " + condition;
        }
        return sql;
    }
}
