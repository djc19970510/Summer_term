package cn.edu.zucc.ding.summerterm.model;

import java.sql.Timestamp;
import java.util.Date;

public class OrderAndProduction {
    private int ID;
    private int ProductionID;
    private double ProductionNumber;
    private Timestamp date;
    private String Name;

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

    public double getProductionNumber() {
        return ProductionNumber;
    }

    public void setProductionNumber(double productionNumber) {
        ProductionNumber = productionNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public OrderAndProduction(int ID, int productionID, double productionNumber, Timestamp date, String name) {
        this.ID = ID;
        ProductionID = productionID;
        ProductionNumber = productionNumber;
        this.date = date;
        Name = name;
    }
}
