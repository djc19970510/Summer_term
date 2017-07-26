package cn.edu.zucc.ding.summerterm.model;

import java.sql.Timestamp;

public class ProductionsAndOrder {
    private int ID;
    private double price;
    private double number;
    private int productionid;
    private int customerid;
    private String productionname;
    private String customername;
    private Timestamp date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public int getProductionid() {
        return productionid;
    }

    public void setProductionid(int productionid) {
        this.productionid = productionid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getProductionname() {
        return productionname;
    }

    public void setProductionname(String productionname) {
        this.productionname = productionname;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ProductionsAndOrder(int ID, double price, double number, int productionid, int customerid, String productionname, String customername, Timestamp date) {
        this.ID = ID;
        this.price = price;
        this.number = number;
        this.productionid = productionid;
        this.customerid = customerid;
        this.productionname = productionname;
        this.customername = customername;
        this.date = date;
    }
}
