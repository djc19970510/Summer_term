package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;

public class Productingorder implements getID {
    private int ID;
    private int ProductionID;
    private double ProductionNumber;
    private Date date;

    public Productingorder() {
    }

    public Productingorder(int ID, int ProductionID, double ProductionNumber, Date date) {
        this();
        this.ID = ID;
        this.ProductionID = ProductionID;
        this.ProductionNumber = ProductionNumber;
        this.date = date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }

    public void setProductionNumber(double ProductionNumber) {
        this.ProductionNumber = ProductionNumber;
    }

    public double getProductionNumber() {
        return ProductionNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}