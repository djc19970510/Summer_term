package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Supplier implements getID {
    private int ID;
    private String Name;
    private String Address;
    private int LinkID;
    private String Introduction;

    public Supplier() {
    }

    public Supplier(int ID, String Name, String Address, int LinkID, String Introduction) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.LinkID = LinkID;
        this.Introduction = Introduction;
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

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setLinkID(int LinkID) {
        this.LinkID = LinkID;
    }

    public int getLinkID() {
        return LinkID;
    }

    public void setIntroduction(String Introduction) {
        this.Introduction = Introduction;
    }

    public String getIntroduction() {
        return Introduction;
    }
}