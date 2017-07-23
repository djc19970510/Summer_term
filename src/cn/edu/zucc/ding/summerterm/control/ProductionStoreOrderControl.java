package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductionstoreorderControl;
import cn.edu.zucc.ding.summerterm.model.Productionstoreorder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionStoreOrderControl implements IProductionstoreorderControl {
    @Override
    public List<Productionstoreorder> loadAllProductionstoreorder() {
        List<Productionstoreorder> result = new ArrayList<Productionstoreorder>();
        String sql = DatabaseOP.select("*","productionstoreorder","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productionstoreorder p = new Productionstoreorder(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void modifyProductionstoreorder(Productionstoreorder productionstoreorder) {
        String sql = DatabaseOP.update(productionstoreorder);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productionstoreorder.getProductionID());
            pst.setDouble(2,productionstoreorder.getNumber());
            pst.setDate(3,productionstoreorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductionstoreorder(Productionstoreorder productionstoreorder) {
        String sql = DatabaseOP.insert(productionstoreorder);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productionstoreorder.getProductionID());
            pst.setDouble(2,productionstoreorder.getNumber());
            pst.setDate(3,productionstoreorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductionstoreorder(Productionstoreorder productionstoreorder) {
        String sql = DatabaseOP.delete(productionstoreorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
