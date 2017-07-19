package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Productiondetails implements getID {
    private int ProductionID;
    private int MaterialsID;
    private double MaterialsNumber;
    private int ID;

    public Productiondetails() {
    }

    public Productiondetails(int ProductionID, int MaterialsID, double MaterialsNumber, int ID) {
        this();
        this.ProductionID = ProductionID;
        this.MaterialsID = MaterialsID;
        this.MaterialsNumber = MaterialsNumber;
        this.ID = ID;
    }

    public void setProductionID(int ProductionID) {
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}