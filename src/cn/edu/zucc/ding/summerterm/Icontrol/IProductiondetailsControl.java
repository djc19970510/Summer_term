
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productiondetails;

import java.util.List;

public interface IProductiondetailsControl {
    public List<Productiondetails> loadAllProductiondetails();
    public void modifyProductiondetails(Productiondetails productiondetails);
    public void addProductiondetails(Productiondetails productiondetails);
    public void delProductiondetails(Productiondetails productiondetails);
}
