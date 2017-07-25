package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductingorderControl;
import cn.edu.zucc.ding.summerterm.model.Productingorder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductingOrderControl implements IProductingorderControl {
    @Override
    public List<Productingorder> loadAllProductingorder() {
        List<Productingorder> result = null;
        String sql = DatabaseOP.select("*","productingorder","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Productingorder>();
            while(rs.next()){
                Productingorder s = new Productingorder(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4)
                );
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void modifyProductingorder(Productingorder productingorder) {
        String sql = DatabaseOP.update(productingorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setInt(1,productingorder.getProductionID());
            pst.setDouble(2, productingorder.getProductionNumber());
            pst.setTimestamp(3,productingorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductingorder(Productingorder productingorder) {
        String sql = DatabaseOP.insert(productingorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setInt(1,productingorder.getProductionID());
            pst.setDouble(2, productingorder.getProductionNumber());
            pst.setTimestamp(3,productingorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductingorder(Productingorder productingorder) {
        String sql = DatabaseOP.delete(productingorder);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
