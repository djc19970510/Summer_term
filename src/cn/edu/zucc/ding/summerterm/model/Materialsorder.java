package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Materialsorder implements getID {
    private int ID;
    private double Price;
    private double Number;
    private int MaterialsID;

    public Materialsorder() {
    }

    public Materialsorder(int ID, double Price, double Number, int MaterialsID) {
        this();
        this.ID = ID;
        this.Price = Price;
        this.Number = Number;
        this.MaterialsID = MaterialsID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getPrice() {
        return Price;
    }

    public void setNumber(double Number) {
        this.Number = Number;
    }

    public double getNumber() {
        return Number;
    }

    public void setMaterialsID(int MaterialsID) {
        this.MaterialsID = MaterialsID;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }
}