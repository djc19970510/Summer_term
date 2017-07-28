package cn.edu.zucc.ding.summerterm;

import cn.edu.zucc.ding.summerterm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class TestSystem {
    public static void main(String[] args){
        try {
            java.util.Random random=new java.util.Random();
            Connection conn = DBUtil.getConnection();
            String sql = "insert into production(name,price,ProductionTypeID) values(?,?,?)";
            for(int i=167;i<267;i++){
                PreparedStatement pst = conn.prepareStatement(sql);
                int rmp = random.nextInt(10)+1;
                for(int j=0;j<rmp;j++){

                }
                pst.execute();
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
