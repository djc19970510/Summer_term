package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;
import java.sql.Timestamp;

public class Productingorder implements getID {
    private int ID;
    private int ProductionID;
    private double ProductionNumber;
    private Timestamp date;

    public Productingorder() {
    }

    public Productingorder(int ID, int ProductionID, double ProductionNumber, Timestamp date) {
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}