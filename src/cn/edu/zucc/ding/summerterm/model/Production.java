package cn.edu.zucc.ding.summerterm.model;

public class Production {
    private int ID;
    private String Name;
    private double Price;
    private int ProductionTypeID;

    public Production() {
    }

    public Production(int ID, String Name, double Price, int ProductionTypeID) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Price = Price;
        this.ProductionTypeID = ProductionTypeID;
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

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getPrice() {
        return Price;
    }

    public void setProductionTypeID(int ProductionTypeID) {
        this.ProductionTypeID = ProductionTypeID;
    }

    public int getProductionTypeID() {
        return ProductionTypeID;
    }
}