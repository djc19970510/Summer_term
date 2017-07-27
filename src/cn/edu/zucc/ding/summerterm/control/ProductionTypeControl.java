package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductiontypeControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiontype;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionTypeControl implements IProductiontypeControl{

    public List<Productiontype> loadSomeProductiontype(String string){
        List<Productiontype> productiontypes = new ArrayList<Productiontype>();
        String sql = DatabaseOP.select("*","Productiontype","where name like '%"+string+"%' or introduction like '%"+string+"%'");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productiontype pt = new Productiontype(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                productiontypes.add(pt);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productiontypes;
    }

    @Override
    public List<Productiontype> loadAllProductiontype() {
        List<Productiontype> productiontypes = new ArrayList<Productiontype>();
        String sql = DatabaseOP.select("*","Productiontype","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Productiontype pt = new Productiontype(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                productiontypes.add(pt);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productiontypes;
    }

    @Override
    public void modifyProductiontype(Productiontype productiontype) {
        String sql = DatabaseOP.update(productiontype);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,productiontype.getName());
            pst.setString(2,productiontype.getIntroduction());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductiontype(Productiontype productiontype) {
        String sql = DatabaseOP.insert(productiontype);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,productiontype.getName());
            pst.setString(2,productiontype.getIntroduction());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProductiontype(Productiontype productiontype) {
        String sql = DatabaseOP.delete(productiontype);
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
