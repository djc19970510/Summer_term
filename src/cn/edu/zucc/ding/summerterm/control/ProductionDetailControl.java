package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductiondetailsControl;
import cn.edu.zucc.ding.summerterm.model.Productiondetails;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionDetailControl implements IProductiondetailsControl {
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

    }

    @Override
    public void addProductiondetails(Productiondetails productiondetails) {

    }

    @Override
    public void delProductiondetails(Productiondetails productiondetails) {

    }
}
