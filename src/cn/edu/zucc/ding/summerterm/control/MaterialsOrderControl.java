package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IMaterialsorderControl;
import cn.edu.zucc.ding.summerterm.model.Materialsorder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialsOrderControl implements IMaterialsorderControl {
    @Override
    public List<Materialsorder> loadAllMaterialsorder() {
        List<Materialsorder> result = null;
        String sql = DatabaseOP.select("*","materialsorder","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Materialsorder>();
            while(rs.next()){
//                Materialsorder s = new Materialsorder(
//                        rs.getInt(1),
//                        rs.getDouble(2),
//                        rs.getDouble(3),
//                        rs.getInt(4)
//                );
//                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void modifyMaterialsorder(Materialsorder materialsorder) {
        String sql = DatabaseOP.update(materialsorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsorder.getPrice());
            pst.setDouble(2,materialsorder.getNumber());
            pst.setInt(3,materialsorder.getMaterialsID());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMaterialsorder(Materialsorder materialsorder) {
        String sql = DatabaseOP.insert(materialsorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsorder.getPrice());
            pst.setDouble(2,materialsorder.getNumber());
            pst.setInt(3,materialsorder.getMaterialsID());
            pst.setTimestamp(4,materialsorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delMaterialsorder(Materialsorder materialsorder) {
        String sql = DatabaseOP.delete(materialsorder);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
