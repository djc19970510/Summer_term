package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductionControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiontype;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionControl implements IProductionControl {
    public List<Production> loadSomeProduction(String a,String low,String high){
        double low1 = 0;
        double high1 = Double.MAX_VALUE;
        if(!(low==null||low.equals("")))
            low1 = Double.valueOf(low);
        if(!(high==null||high.equals("")))
            high1 = Double.valueOf(high);
        List<Production> result = new ArrayList<Production>();
        String sql = DatabaseOP.select("*","production","where (price>=? and price<=?) and name like ?");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1,low1);
            pst.setDouble(2,high1);
            pst.setString(3,"%"+a+"%");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Production p = new Production(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                );
                result.add(p);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Production> loadSomeProduction(Productiontype pt){
        List<Production> result = new ArrayList<Production>();
        String sql = DatabaseOP.select("*","production","where productiontypeid="+pt.getID());
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Production p = new Production(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
                );
                result.add(p);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Production> loadAllProduction() {
        List<Production> productions = new ArrayList<Production>();
        String sql = DatabaseOP.select("*","Production","");
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Production p = new Production(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)
                );
                productions.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productions;
    }

    @Override
    public void modifyProduction(Production production) {
        String sql = DatabaseOP.update(production);
        System.out.print(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,production.getName());
            pst.setDouble(2,production.getPrice());
            pst.setInt(3,production.getProductionTypeID());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduction(Production production) {
        String sql = DatabaseOP.insert(production);
        System.out.print(sql);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,production.getName());
            pst.setDouble(2,production.getPrice());
            pst.setInt(3,production.getProductionTypeID());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delProduction(Production production) {
        String sql = DatabaseOP.delete(production);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
