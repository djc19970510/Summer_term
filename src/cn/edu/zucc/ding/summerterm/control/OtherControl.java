package cn.edu.zucc.ding.summerterm.control;

import cn.edu.zucc.ding.summerterm.model.*;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtherControl {
    public List<ProductionsAndOrder> loadAllProductionsAndOrder(){
        List<ProductionsAndOrder> result = new ArrayList<ProductionsAndOrder>();
        String sql = "SELECT productionorder.ID,productionorder.price,number,ProductionID,CustomerID," +
                "Date,production.Name,customer.Name FROM productionorder,production,customer " +
                "where productionorder.ProductionID=production.id and customer.id=productionorder.CustomerID;";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ProductionsAndOrder m = new ProductionsAndOrder(
                    rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getTimestamp(6)
                );
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaterialsAndOrder> loadAllMaterialsAndOrder(){
        List<MaterialsAndOrder> result = new ArrayList<MaterialsAndOrder>();
        String sql = "SELECT materialsorder.id as orderid,materials.name as materialsname," +
                "materials.id as materialsid,materials.MaterialsBasePrice,supplierID,supplier.name,Date,materialsorder.Number " +
                "FROM materials,materialsorder,supplier where materials.ID=materialsorder.MaterialsID " +
                "And supplier.id=SupplierID;";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndOrder m = new MaterialsAndOrder(
                        rs.getInt(1),
                        rs.getDouble(4),
                        rs.getDouble(8),
                        rs.getInt(3),
                        rs.getString(2),
                        rs.getTimestamp(7),
                        rs.getInt(5),
                        rs.getString(6)
                );
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderAndProduction> loadAllProductingorder(){
        List<OrderAndProduction> result = new ArrayList<OrderAndProduction>();
        String sql = "SELECT productingorder.id,ProductionID,ProductionNumber,Date,name FROM productingorder,production where ProductionID=production.id order by Date";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                OrderAndProduction m = new OrderAndProduction(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getTimestamp(4),
                        rs.getString(5)
                );
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

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

    public List<MaterialsAndSuppliers> loadSomeMaterialsAndSupplier(){
        List<MaterialsAndSuppliers> result = new ArrayList<MaterialsAndSuppliers>();
        String sql = "select materials.id,materials.name,MaterialsBasePrice,materials.Introduction,supplier.id,supplier.name " +
                "FROM materials,supplier where SupplierID=supplier.ID";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndSuppliers ms = new MaterialsAndSuppliers(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
                result.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<ProductionAndStore> loadAllProductionsAndStores(){
        List<ProductionAndStore> result = new ArrayList<ProductionAndStore>();
        String sql = "SELECT productionstore.id,Number,Address,ProductionID,Name FROM production,productionstore where production.ID=productionstore.ProductionID;";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ProductionAndStore pas = new ProductionAndStore(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                result.add(pas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<ProductionAndStore> loadAllProductionsAndStores(String s){
        s = "%"+s+"%";
        List<ProductionAndStore> result = new ArrayList<ProductionAndStore>();
        String sql = "SELECT productionstore.id,Number,Address,ProductionID,Name FROM production,productionstore where production.ID=productionstore.ProductionID" +
                " and (Address like ? or Name like ?)";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,s);
            pst.setString(2,s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ProductionAndStore pas = new ProductionAndStore(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                result.add(pas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<MaterialsAndStore> loadAllMaterialsAndStore(){
        List<MaterialsAndStore> result = new ArrayList<MaterialsAndStore>();
        String sql = "SELECT materialsstore.id,number,materialsstore.address,MaterialsID,materials.name,SupplierID,supplier.name" +
                " FROM materialsstore,materials,supplier " +
                " where materials.id=materialsstore.MaterialsID and supplier.ID=SupplierID";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndStore mas = new MaterialsAndStore(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                );
                result.add(mas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaterialsAndStore> loadAllMaterialsAndStore(String s){
        s = "%"+s+"%";
        List<MaterialsAndStore> result = new ArrayList<MaterialsAndStore>();
        String sql = "SELECT materialsstore.id,number,materialsstore.address,MaterialsID,materials.name,SupplierID,supplier.name" +
                " FROM materialsstore,materials,supplier " +
                " where materials.id=materialsstore.MaterialsID and supplier.ID=SupplierID "+
                " and (materialsstore.address like ? or materials.name like ? or supplier.name like ?)";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,s);
            pst.setString(2,s);
            pst.setString(3,s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndStore mas = new MaterialsAndStore(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                );
                result.add(mas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaterialsAndSuppliers> loadSomeMaterialsAndSupplier(String s){
        List<MaterialsAndSuppliers> result = new ArrayList<MaterialsAndSuppliers>();
        s = "%"+s+"%";
        String sql = "select materials.id,materials.name,MaterialsBasePrice,materials.Introduction,supplier.id,supplier.name " +
                "FROM materials,supplier where SupplierID=supplier.ID and (" +
                "materials.name like ? or MaterialsBasePrice like ? or materials.Introduction like ?" +
                " or supplier.name like ?)";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,s);
            pst.setString(2,s);
            pst.setString(3,s);
            pst.setString(4,s);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndSuppliers ms = new MaterialsAndSuppliers(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
                result.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
