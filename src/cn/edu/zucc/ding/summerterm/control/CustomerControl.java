package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.ICustomerControl;
import cn.edu.zucc.ding.summerterm.model.Customer;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerControl implements ICustomerControl{

    @Override
    public List<Customer> loadAllCustomer() {
        List<Customer> result = null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = DatabaseOP.select("*","customer","");
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();

                result.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void modifyCustomer(Customer customer) {

    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void delCustomer(Customer customer) {

    }
}
