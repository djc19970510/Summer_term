package cn.edu.zucc.ding.summerterm.model;

public class Productiontype {
    private int ID;
    private String Name;
    private String Introduction;

    public Productiontype() {
    }

    public Productiontype(int ID, String Name, String Introduction) {
        this();
        this.ID = ID;
        this.Name = Name;
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

    public void setIntroduction(String Introduction) {
        this.Introduction = Introduction;
    }

    public String getIntroduction() {
        return Introduction;
    }
}