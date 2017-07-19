
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productionorder;

import java.util.List;

public interface IProductionorderControl {
    public List<Productionorder> loadAllProductionorder();
    public void modifyProductionorder(Productionorder productionorder);
    public void addProductionorder(Productionorder productionorder);
    public void delProductionorder(Productionorder productionorder);
}
