package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

public class Customer implements getID {
    private int ID;
    private String Name;
    private String Address;
    private String LinkName;
    private String LinkPhone;

    public Customer() {
    }

    public Customer(int ID, String Name, String Add, String LinkName, String LinkPhone) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Address = Add;
        this.LinkName = LinkName;
        this.LinkPhone = LinkPhone;
    }

    @Override
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLinkName() {
        return LinkName;
    }

    public void setLinkName(String linkName) {
        LinkName = linkName;
    }

    public String getLinkPhone() {
        return LinkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        LinkPhone = linkPhone;
    }
}