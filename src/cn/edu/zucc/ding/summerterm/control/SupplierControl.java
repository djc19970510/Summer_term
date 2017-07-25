package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.ISupplierControl;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierControl implements ISupplierControl {

    public List<Supplier> loadSomeSupplier(String phrase){
        phrase = "%"+phrase+"%";
        List<Supplier> result = null;
        String sql = DatabaseOP.select("*","Supplier",
                "where Name like ? or Address like ? or LinkName like ?" +
                        " or Introduction like ? or LinkPhone like ?");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,phrase);
            pst.setString(2,phrase);
            pst.setString(3,phrase);
            pst.setString(4,phrase);
            pst.setString(5,phrase);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Supplier>();
            while(rs.next()){
                Supplier s = new Supplier(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Supplier> loadAllSupplier() {
        List<Supplier> result = null;
        String sql = DatabaseOP.select("*","Supplier","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Supplier>();
            while(rs.next()){
                Supplier s = new Supplier(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void modifySupplier(Supplier s) {
        String sql = DatabaseOP.update(s);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            System.out.println(sql);
            pst.setString(1,s.getName());
            pst.setString(2,s.getAddress());
            pst.setString(3,s.getLinkName());
            pst.setString(4,s.getIntroduction());
            pst.setString(5,s.getLinkPhone());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSupplier(Supplier supplier) {
        String sql = DatabaseOP.insert(supplier);
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst= conn.prepareStatement(sql);
            pst.setString(1,supplier.getName());
            pst.setString(2,supplier.getAddress());
            pst.setString(3,supplier.getLinkName());
            pst.setString(4,supplier.getIntroduction());
            pst.setString(5,supplier.getLinkPhone());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delSupplier(Supplier supplier) {
        String sql = DatabaseOP.delete(supplier);
        try {
            Connection conn =DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
