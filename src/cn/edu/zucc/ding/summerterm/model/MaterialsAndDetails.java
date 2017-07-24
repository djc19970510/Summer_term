package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class MaterialsAndDetails implements getID {
    private int ID;
    private int ProductionID;
    private int MaterialsID;
    private String Name;
    private Double Number;

    public MaterialsAndDetails(int ID, int materialsID, String name, Double number, int ProductionID) {
        this.MaterialsID = materialsID;
        this.ID = ID;
        this.Name = name;
        this.Number = number;
        this.ProductionID = ProductionID;
    }

    public int getProductionID() {
        return ProductionID;
    }

    public void setProductionID(int productionID) {
        ProductionID = productionID;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }

    public void setMaterialsID(int materialsID) {
        MaterialsID = materialsID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getNumber() {
        return Number;
    }

    public void setNumber(Double number) {
        Number = number;
    }
}
