package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Timestamp;

public class Materialsorder implements getID {
    private int ID;
    private double Price;
    private double Number;
    private int MaterialsID;
    private Timestamp Date;

    @Override
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

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }

    public Materialsorder(int ID, double price, double number, int materialsID, Timestamp date) {
        this.ID = ID;
        Price = price;
        Number = number;
        MaterialsID = materialsID;
        Date = date;
    }
}