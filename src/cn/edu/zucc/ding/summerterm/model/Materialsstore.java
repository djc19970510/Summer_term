package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Materialsstore implements getID {
    private int ID;
    private double Number;
    private String Address;
    private int MaterialsID;

    @Override
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

    public Materialsstore(int ID, double number, String address, int materialsID) {

        this.ID = ID;
        Number = number;
        Address = address;
        MaterialsID = materialsID;
    }
}