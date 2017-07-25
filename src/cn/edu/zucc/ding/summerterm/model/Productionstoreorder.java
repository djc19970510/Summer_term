package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;
import java.sql.Timestamp;

public class Productionstoreorder implements getID {
    private int ID;
    private int ProductionID;
    private double Number;
    private Timestamp Date;
    private int OrderID;

    @Override
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

    public Productionstoreorder(int ID, int productionID, double number, Timestamp date, int orderID) {
        this.ID = ID;
        ProductionID = productionID;
        Number = number;
        Date = date;
        OrderID = orderID;
    }
}