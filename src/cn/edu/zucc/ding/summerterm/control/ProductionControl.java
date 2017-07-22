package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.Icontrol.IProductionControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionControl implements IProductionControl {
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

    }

    @Override
    public void addProduction(Production production) {

    }

    @Override
    public void delProduction(Production production) {

    }
}
