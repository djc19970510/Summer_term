package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Materialsstore implements getID {
    private int ID;
    private double Number;
    private String Add;
    private int MaterialsID;

    public Materialsstore() {
    }

    public Materialsstore(int ID, double Number, String Add, int MaterialsID) {
        this();
        this.ID = ID;
        this.Number = Number;
        this.Add = Add;
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

    public void setAdd(String Add) {
        this.Add = Add;
    }

    public String getAdd() {
        return Add;
    }

    public void setMaterialsID(int MaterialsID) {
        this.MaterialsID = MaterialsID;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }

}