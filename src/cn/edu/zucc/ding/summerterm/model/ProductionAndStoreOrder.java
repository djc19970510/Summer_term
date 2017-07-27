package cn.edu.zucc.ding.summerterm.model;

import java.sql.Timestamp;

public class ProductionAndStoreOrder {
    private int ID;
    private int ProductionID;
    private double Number;
    private Timestamp Date;
    private int OrderID;
    private String ProductionName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductionID() {
        return ProductionID;
    }

    public void setProductionID(int productionID) {
        ProductionID = productionID;
    }

    public double getNumber() {
        return Number;
    }

    public void setNumber(double number) {
        Number = number;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getProductionName() {
        return ProductionName;
    }

    public void setProductionName(String productionName) {
        ProductionName = productionName;
    }

    public ProductionAndStoreOrder(int ID, int productionID, double number, Timestamp date, int orderID, String productionName) {
        this.ID = ID;
        ProductionID = productionID;
        Number = number;
        Date = date;
        OrderID = orderID;
        ProductionName = productionName;
    }
}
