package cn.edu.zucc.ding.summerterm.model;

import java.sql.Timestamp;

public class MaterialsAndOrder {
    private int ID;
    private double Price;
    private double Number;
    private int MaterialsID;
    private String MaterialsName;
    private Timestamp Date;
    private int supplierID;
    private String supplierName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getNumber() {
        return Number;
    }

    public void setNumber(double number) {
        Number = number;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }

    public void setMaterialsID(int materialsID) {
        MaterialsID = materialsID;
    }

    public String getMaterialsName() {
        return MaterialsName;
    }

    public void setMaterialsName(String materialsName) {
        MaterialsName = materialsName;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public MaterialsAndOrder(int ID, double price, double number, int materialsID, String materialsName, Timestamp date, int supplierID, String supplierName) {
        this.ID = ID;
        Price = price;
        Number = number;
        MaterialsID = materialsID;
        MaterialsName = materialsName;
        Date = date;
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }
}
