package cn.edu.zucc.ding.summerterm.model;

public class ProductionAndStore {
    private int ID;
    private double Number;
    private String Address;
    private int ProductionID;
    private String Name;

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

    public int getProductionID() {
        return ProductionID;
    }

    public void setProductionID(int productionID) {
        ProductionID = productionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ProductionAndStore(int ID, double number, String address, int productionID, String name) {
        this.ID = ID;
        Number = number;
        Address = address;
        ProductionID = productionID;
        Name = name;
    }
}
