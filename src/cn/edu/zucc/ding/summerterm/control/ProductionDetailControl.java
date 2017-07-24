package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductiondetailsControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiondetails;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionDetailControl implements IProductiondetailsControl {
    public List<Productiondetails> loadSomeProductiondetails(Production pt){
        List<Productiondetails> result = new ArrayList<Productiondetails>();
        String sql = DatabaseOP.select("*","productiondetails","where productionid="+pt.getID());
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productiondetails p = new Productiondetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public List<Productiondetails> loadAllProductiondetails() {
        List<Productiondetails> result = new ArrayList<Productiondetails>();
        String sql = DatabaseOP.select("*","productiondetails","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productiondetails p = new Productiondetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                );
                result.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void modifyProductiondetails(Productiondetails productiondetails) {
        String sql = DatabaseOP.update(productiondetails);
        System.out.println(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productiondetails.getProductionID());
            pst.setInt(2,productiondetails.getMaterialsID());
            pst.setDouble(3,productiondetails.getMaterialsNumber());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductiondetails(Productiondetails productiondetails) {
        String sql = DatabaseOP.insert(productiondetails);
        System.out.print(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,productiondetails.getProductionID());
            pst.setInt(2,productiondetails.getMaterialsID());
            pst.setDouble(3,productiondetails.getMaterialsNumber());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductiondetails(Productiondetails productiondetails) {
        String sql = DatabaseOP.delete(productiondetails);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
