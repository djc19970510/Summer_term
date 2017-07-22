package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.sql.Date;
public class Productionorder implements getID {
    private int ID;

    private double price;
    private double number;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Productionorder() {
    }

    public Productionorder(int ID, double price, double number, int ProductionID, int CustomerID) {
        this();
        this.ID = ID;
        this.price = price;
        this.number = number;
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
}