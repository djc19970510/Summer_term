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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productiontypes;
    }

    @Override
    public void modifyProductiontype(Productiontype productiontype) {

    }

    @Override
    public void addProductiontype(Productiontype productiontype) {

    }

    @Override
    public void delProductiontype(Productiontype productiontype) {

    }
}
