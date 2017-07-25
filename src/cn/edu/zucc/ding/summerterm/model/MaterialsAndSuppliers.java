package cn.edu.zucc.ding.summerterm.model;

public class MaterialsAndSuppliers {
    private int ID;
    private String Name;
    private double MaterialsBasePrice;
    private String Introduction;
    private int SupplierID;
    private String SupplierName;

    public MaterialsAndSuppliers(int ID, String name, double materialsBasePrice, String introduction, int supplierID, String supplierName) {
        this.ID = ID;
        Name = name;
        MaterialsBasePrice = materialsBasePrice;
        Introduction = introduction;
        SupplierID = supplierID;
        SupplierName = supplierName;
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

    public double getMaterialsBasePrice() {
        return MaterialsBasePrice;
    }

    public void setMaterialsBasePrice(double materialsBasePrice) {
        MaterialsBasePrice = materialsBasePrice;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

}
