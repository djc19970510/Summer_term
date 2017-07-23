package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;

public class Materialsstoreorder implements getID {
    private int ID;
    private double Number;
    private Date Date;
    private int MaterialsID;

    public Materialsstoreorder() {
    }

    public Materialsstoreorder(int ID, double Number, Date Date, int MaterialsID) {
        this();
        this.ID = ID;
        this.Number = Number;
        this.Date = Date;
        this.MaterialsID = MaterialsID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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

    public void setMaterialsID(int MaterialsID) {
        this.MaterialsID = MaterialsID;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }
}