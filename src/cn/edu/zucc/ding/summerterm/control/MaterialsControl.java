package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IMaterialsControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import cn.edu.zucc.ding.summerterm.util.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialsControl implements IMaterialsControl {

    @Override
    public List<Materials> loadAllMaterials() {
        List<Materials> result = null;
        String sql = DatabaseOP.select("*","materials","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Materials>();
            while(rs.next()){
                Materials s = new Materials(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5)
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
    public void modifyMaterials(Materials materials) {
        String sql = DatabaseOP.update(materials);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setString(1,materials.getName());
            pst.setDouble(2,materials.getMaterialsBasePrice());
            pst.setString(3,materials.getIntroduction());
            pst.setInt(4,materials.getSupplierID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new DbException(e);
            } catch (DbException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void addMaterials(Materials materials) {
        String sql = DatabaseOP.insert(materials);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            pst.setString(1,materials.getName());
            pst.setDouble(2,materials.getMaterialsBasePrice());
            pst.setString(3,materials.getIntroduction());
            pst.setInt(4,materials.getSupplierID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new DbException(e);
            } catch (DbException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void delMaterials(Materials materials) {
        String sql = DatabaseOP.delete(materials);
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
