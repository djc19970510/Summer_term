package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Productionstore implements getID {
    private int ID;
    private double Number;
    private String Address;
    private int ProductionID;

    public Productionstore() {
    }

    public Productionstore(int ID, double Number, String Address, int ProductionID) {
        this();
        this.ID = ID;
        this.Number = Number;
        this.Address = Address;
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

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }
}