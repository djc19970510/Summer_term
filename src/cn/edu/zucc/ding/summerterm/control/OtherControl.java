package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.model.MaterialsAndDetails;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtherControl {
    public List<MaterialsAndDetails> loadSomeMaterialsAndDetails(Production pt){
        List<MaterialsAndDetails> result = new ArrayList<MaterialsAndDetails>();
        String sql = DatabaseOP.select("productiondetails.id,MaterialsID,name,MaterialsNumber,ProductionID",
                "productiondetails,materials",
                "where productiondetails.MaterialsID=materials.ID and productionid = "+pt.getID());
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndDetails m = new MaterialsAndDetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)
                );
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
