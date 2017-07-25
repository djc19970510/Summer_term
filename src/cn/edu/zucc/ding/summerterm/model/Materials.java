package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Materials implements getID {
    private int ID;
    private String Name;
    private double MaterialsBasePrice;
    private String Introduction;
    private int SupplierID;

    public Materials() {}

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public Materials(int ID, String Name, double MaterialsBasePrice, String Introduction, int SupplierID) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.MaterialsBasePrice = MaterialsBasePrice;
        this.Introduction = Introduction;
        this.SupplierID = SupplierID;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setMaterialsBasePrice(double MaterialsBasePrice) {
        this.MaterialsBasePrice = MaterialsBasePrice;
    }

    public double getMaterialsBasePrice() {
        return MaterialsBasePrice;
    }

    public void setIntroduction(String Introduction) {
        this.Introduction = Introduction;
    }

    public String getIntroduction() {
        return Introduction;
    }
}