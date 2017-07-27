package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IMaterialsstoreControl;
import cn.edu.zucc.ding.summerterm.model.Materialsstore;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialsStoreControl implements IMaterialsstoreControl {

    @Override
    public List<Materialsstore> loadAllMaterialsstore() {
        List<Materialsstore> result = null;
        String sql = DatabaseOP.select("*","materialsstore","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Materialsstore>();
            while(rs.next()){
                Materialsstore s = new Materialsstore(
                        rs.getInt(1),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(2)
                );
                result.add(s);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void modifyMaterialsstore(Materialsstore materialsstore) {
        String sql = DatabaseOP.update(materialsstore);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsstore.getNumber());
            pst.setString(2,materialsstore.getAddress());
            pst.setInt(3,materialsstore.getMaterialsID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMaterialsstore(Materialsstore materialsstore) {
        String sql = DatabaseOP.insert(materialsstore);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setDouble(1,materialsstore.getNumber());
            pst.setString(2,materialsstore.getAddress());
            pst.setInt(3,materialsstore.getMaterialsID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delMaterialsstore(Materialsstore materialsstore) {
        String sql = DatabaseOP.delete(materialsstore);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
