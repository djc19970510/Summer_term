package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductionorderControl;
import cn.edu.zucc.ding.summerterm.model.Productionorder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionOrderControl implements IProductionorderControl {

    @Override
    public List<Productionorder> loadAllProductionorder() {
        List<Productionorder> result = new ArrayList<Productionorder>();
        String sql = DatabaseOP.select("*","productionorder","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productionorder p = new Productionorder(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getInt(5),
                        rs.getInt(6)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void modifyProductionorder(Productionorder productionorder) {
        String sql = DatabaseOP.update(productionorder);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1,productionorder.getPrice());
            pst.setDouble(2,productionorder.getNumber());
            pst.setTimestamp(3,productionorder.getDate());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductionorder(Productionorder productionorder) {
        String sql = DatabaseOP.insert(productionorder);
        System.out.print(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1,productionorder.getPrice());
            pst.setDouble(2,productionorder.getNumber());
            pst.setTimestamp(3,productionorder.getDate());
            pst.setInt(4,productionorder.getCustomerid());
            pst.setInt(5,productionorder.getProductionid());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductionorder(Productionorder productionorder) {
        String sql = DatabaseOP.delete(productionorder);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
