package cn.edu.zucc.ding.summerterm.model;

public class Materialsstore {
    private int MaterialsStoreID;
    private double Number;
    private String Add;
    private int MaterialsID;

    public Materialsstore() {
    }

    public Materialsstore(int MaterialsStoreID, double Number, String Add, int MaterialsID) {
        this();
        this.MaterialsStoreID = MaterialsStoreID;
        this.Number = Number;
        this.Add = Add;
        this.MaterialsID = MaterialsID;
    }

    public void setMaterialsStoreID(int MaterialsStoreID) {
        this.MaterialsStoreID = MaterialsStoreID;
    }

    public int getMaterialsStoreID() {
        return MaterialsStoreID;
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