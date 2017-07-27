package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductionstoreControl;
import cn.edu.zucc.ding.summerterm.model.Productionstore;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionStoreControl implements IProductionstoreControl {
    @Override
    public List<Productionstore> loadAllProductionstore() {
        List<Productionstore> result = new ArrayList<Productionstore>();
        String sql = DatabaseOP.select("*","productionstore","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productionstore p = new Productionstore(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                result.add(p);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void modifyProductionstore(Productionstore productionstore) {
        String sql = DatabaseOP.update(productionstore);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1,productionstore.getNumber());
            pst.setString(2,productionstore.getAddress());
            pst.setInt(3,productionstore.getProductionID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductionstore(Productionstore productionstore) {
        String sql = DatabaseOP.insert(productionstore);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1,productionstore.getNumber());
            pst.setString(2,productionstore.getAddress());
            pst.setInt(3,productionstore.getProductionID());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductionstore(Productionstore productionstore) {
        String sql = DatabaseOP.delete(productionstore);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
