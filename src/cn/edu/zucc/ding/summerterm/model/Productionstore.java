package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Productionstore implements getID {
    private int ID;
    private double Number;
    private String Add;
    private int ProductionID;

    public Productionstore() {
    }

    public Productionstore(int ID, double Number, String Add, int ProductionID) {
        this();
        this.ID = ID;
        this.Number = Number;
        this.Add = Add;
        this.ProductionID = ProductionID;
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

    public void setAdd(String Add) {
        this.Add = Add;
    }

    public String getAdd() {
        return Add;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }
}