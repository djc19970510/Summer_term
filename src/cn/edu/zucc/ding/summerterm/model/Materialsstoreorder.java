package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;
import java.sql.Timestamp;

public class Materialsstoreorder implements getID {
    private int ID;
    private double Number;
    private Timestamp Date;
    private int MaterialsID;
    private int OrderID;

    @Override
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

    public Materialsstoreorder(int ID, double number, Timestamp date, int materialsID, int orderID) {
        this.ID = ID;
        Number = number;
        Date = date;
        MaterialsID = materialsID;
        OrderID = orderID;
    }
}