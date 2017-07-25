package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IMaterialsstoreorderControl;
import cn.edu.zucc.ding.summerterm.model.Materialsstoreorder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialsStoreOrderControl implements IMaterialsstoreorderControl {
    @Override
    public List<Materialsstoreorder> loadAllMaterialsstoreorder() {
        List<Materialsstoreorder> result = null;
        String sql = DatabaseOP.select("*","materialsstoreorder","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Materialsstoreorder>();
            while(rs.next()){
                Materialsstoreorder s = new Materialsstoreorder(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getTimestamp(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void modifyMaterialsstoreorder(Materialsstoreorder materialsstoreorder) {
        String sql = DatabaseOP.update(materialsstoreorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsstoreorder.getNumber());
            pst.setTimestamp(2, materialsstoreorder.getDate());
            pst.setInt(3,materialsstoreorder.getMaterialsID());
            pst.setInt(4,materialsstoreorder.getOrderID());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMaterialsstoreorder(Materialsstoreorder materialsstoreorder) {
        String sql = DatabaseOP.insert(materialsstoreorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsstoreorder.getNumber());
            pst.setTimestamp(2, materialsstoreorder.getDate());
            pst.setInt(3,materialsstoreorder.getMaterialsID());
            pst.setInt(4,materialsstoreorder.getOrderID());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delMaterialsstoreorder(Materialsstoreorder materialsstoreorder) {
        String sql = DatabaseOP.delete(materialsstoreorder);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
