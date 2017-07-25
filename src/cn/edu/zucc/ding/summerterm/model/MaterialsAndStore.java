package cn.edu.zucc.ding.summerterm.model;

public class MaterialsAndStore {
    private int ID;
    private double Number;
    private String Address;
    private int MaterialsID;
    private String Name;
    private int SupplierID;
    private String SupplierName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getNumber() {
        return Number;
    }

    public void setNumber(double number) {
        Number = number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getMaterialsID() {
        return MaterialsID;
    }

    public void setMaterialsID(int materialsID) {
        MaterialsID = materialsID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public MaterialsAndStore(int ID, double number, String address, int materialsID, String name, int supplierID, String supplierName) {
        this.ID = ID;
        Number = number;
        Address = address;
        MaterialsID = materialsID;
        Name = name;
        SupplierID = supplierID;
        SupplierName = supplierName;
    }
}
