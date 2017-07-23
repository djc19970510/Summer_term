package cn.edu.zucc.ding.summerterm;

import cn.edu.zucc.ding.summerterm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSystem {
    public static void main(String[] args){
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "insert into materials(name,materialsBasePrice,Introduction) values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
