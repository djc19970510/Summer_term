package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.ICustomerControl;
import cn.edu.zucc.ding.summerterm.model.Customer;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerControl implements ICustomerControl {
    public List<Customer> loadSomeCustomer(String phrase) {
        String phrase2 = phrase;
        phrase = "%"+phrase+"%";
        List<Customer> result = null;
        String sql = DatabaseOP.select("*","Customer",
                "where Name like ? or Address like ? or LinkName like ? or LinkPhone like ?");
        if(DatabaseOP.isNumeric(phrase2)){
            sql+=" or id="+phrase2;
        }
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,phrase);
            pst.setString(2,phrase);
            pst.setString(3,phrase);
            pst.setString(4,phrase);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Customer>();
            while(rs.next()){
                Customer s = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
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
    public List<Customer> loadAllCustomer() {
        List<Customer> result = null;
        String sql = DatabaseOP.select("*","Customer","");
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Customer>();
            while(rs.next()){
                Customer s = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
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
    public void modifyCustomer(Customer c) {
        String sql = DatabaseOP.update(c);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst =conn.prepareStatement(sql);
            System.out.println(sql);
            pst.setString(1,c.getName());
            pst.setString(2,c.getAddress());
            pst.setString(3,c.getLinkName());
            pst.setString(4,c.getLinkPhone());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = DatabaseOP.insert(customer);
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst= conn.prepareStatement(sql);
            pst.setString(1,customer.getName());
            pst.setString(2,customer.getAddress());
            pst.setString(3,customer.getLinkName());
            pst.setString(4,customer.getLinkPhone());
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delCustomer(Customer customer) {
        String sql = DatabaseOP.delete(customer);
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
