package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Supplier implements getID {
    private int ID;
    private String Name;
    private String Address;
    private String LinkName;
    private String Introduction;
    private String LinkPhone;

    public Supplier() {
    }

    public Supplier(int ID, String Name, String Address, String LinkName, String Introduction, String LinkPhone) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.LinkName = LinkName;
        this.Introduction = Introduction;
        this.LinkPhone = LinkPhone;
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

    public void setLinkName(String LinkName) {
        this.LinkName = LinkName;
    }

    public String getLinkName() {
        return LinkName;
    }

    public void setIntroduction(String Introduction) {
        this.Introduction = Introduction;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setLinkPhone(String LinkPhone) {
        this.LinkPhone = LinkPhone;
    }

    public String getLinkPhone() {
        return LinkPhone;
    }
}