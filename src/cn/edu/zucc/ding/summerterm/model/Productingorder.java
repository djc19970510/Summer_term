package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Productingorder implements getID {
    private int ID;
    private int ProductionID;
    private double ProductionNumber;
    private int MaterialsID;
    private double MaterialsNumber;

    public Productingorder() {
    }

    public Productingorder(int ID, int ProductionID, double ProductionNumber, int MaterialsID, double MaterialsNumber) {
        this();
        this.ID = ID;
        this.ProductionID = ProductionID;
        this.ProductionNumber = ProductionNumber;
        this.MaterialsID = MaterialsID;
        this.MaterialsNumber = MaterialsNumber;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }

    public void setProductionNumber(double ProductionNumber) {
        this.ProductionNumber = ProductionNumber;
    }

    public double getProductionNumber() {
        return ProductionNumber;
    }

    public void setMaterialsID(int MaterialsID) {
        this.MaterialsID = MaterialsID;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }

    public void setMaterialsNumber(double MaterialsNumber) {
        this.MaterialsNumber = MaterialsNumber;
    }

    public double getMaterialsNumber() {
        return MaterialsNumber;
    }
}