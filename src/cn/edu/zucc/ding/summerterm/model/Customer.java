package cn.edu.zucc.ding.summerterm.model;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;

import java.util.List;

public class Customer implements getID {
    private int ID;
    private String Name;
    private String Add;
    private int LinkID;

    public Customer() {
    }

    public Customer(int ID, String Name, String Add, int LinkID) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Add = Add;
        this.LinkID = LinkID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public List<String> getAll() {
        return null;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setAdd(String Add) {
        this.Add = Add;
    }

    public String getAdd() {
        return Add;
    }

    public void setLinkID(int LinkID) {
        this.LinkID = LinkID;
    }

    public int getLinkID() {
        return LinkID;
    }
}