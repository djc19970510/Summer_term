package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Productionorder implements getID {
    private int ID;
    private double price;
    private double number;
    private int ProductionID;
    private int CustomerID;

    public Productionorder() {
    }

    public Productionorder(int ID, double price, double number, int ProductionID, int CustomerID) {
        this();
        this.ID = ID;
        this.price = price;
        this.number = number;
        this.ProductionID = ProductionID;
        this.CustomerID = CustomerID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getCustomerID() {
        return CustomerID;
    }
}