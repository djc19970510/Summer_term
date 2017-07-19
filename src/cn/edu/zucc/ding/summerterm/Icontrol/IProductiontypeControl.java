
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productiontype;

import java.util.List;

public interface IProductiontypeControl {
    public List<Productiontype> loadAllProductiontype();
    public void modifyProductiontype(Productiontype productiontype);
    public void addProductiontype(Productiontype productiontype);
    public void delProductiontype(Productiontype productiontype);
}
