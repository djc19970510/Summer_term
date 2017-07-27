package cn.edu.zucc.ding.summerterm.model;

import java.sql.Timestamp;

public class MaterialsAndStoreOrder {
    private int ID;
    private double Number;
    private Timestamp Date;
    private int MaterialsID;
    private int OrderID;
    private String MaterialsName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getMaterialsID() {
        return MaterialsID;
    }

    public void setMaterialsID(int materialsID) {
        MaterialsID = materialsID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getMaterialsName() {
        return MaterialsName;
    }

    public void setMaterialsName(String materialsName) {
        MaterialsName = materialsName;
    }

    public MaterialsAndStoreOrder(int ID, double number, Timestamp date, int materialsID, int orderID, String materialsName) {
        this.ID = ID;
        Number = number;
        Date = date;
        MaterialsID = materialsID;
        OrderID = orderID;
        MaterialsName = materialsName;
    }
}
