
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productionstore;

import java.util.List;

public interface IProductionstoreControl {
    public List<Productionstore> loadAllProductionstore();
    public void modifyProductionstore(Productionstore productionstore);
    public void addProductionstore(Productionstore productionstore);
    public void delProductionstore(Productionstore productionstore);
}
