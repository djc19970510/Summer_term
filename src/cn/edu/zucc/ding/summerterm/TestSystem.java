package cn.edu.zucc.ding.summerterm;

import cn.edu.zucc.ding.summerterm.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TestSystem {
    public static void main(String[] args){
        try {
            Connection conn = DBUtil.getConnection();
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
