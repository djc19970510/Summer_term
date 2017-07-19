package cn.edu.zucc.ding.summerterm.model;

public class Linkman {
    private int ID;
    private String Name;
    private String Phone;

    public Linkman() {
    }

    public Linkman(int ID, String Name, String Phone) {
        this();
        this.ID = ID;
        this.Name = Name;
        this.Phone = Phone;
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

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPhone() {
        return Phone;
    }
}