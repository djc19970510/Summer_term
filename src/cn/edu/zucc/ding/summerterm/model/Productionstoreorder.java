package cn.edu.zucc.ding.summerterm.model;

import java.util.Date;

public class Productionstoreorder {
    private int ID;
    private int ProductionID;
    private double Number;
    private Date Date;

    public Productionstoreorder() {
    }

    public Productionstoreorder(int ID, int ProductionID, double Number, Date Date) {
        this();
        this.ID = ID;
        this.ProductionID = ProductionID;
        this.Number = Number;
        this.Date = Date;
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

    public void setNumber(double Number) {
        this.Number = Number;
    }

    public double getNumber() {
        return Number;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Date getDate() {
        return Date;
    }
}