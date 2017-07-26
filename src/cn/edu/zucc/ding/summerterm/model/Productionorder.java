package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;
import java.sql.Timestamp;

public class Productionorder implements getID {
    private int ID;
    private double price;
    private double number;
    private Timestamp date;
    private int customerid;
    private int productionid;

    @Override
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getProductionid() {
        return productionid;
    }

    public void setProductionid(int productionid) {
        this.productionid = productionid;
    }

    public Productionorder(int ID, double price, double number, Timestamp date, int customerid, int productionid) {
        this.ID = ID;
        this.price = price;
        this.number = number;
        this.date = date;
        this.customerid = customerid;
        this.productionid = productionid;
    }
}