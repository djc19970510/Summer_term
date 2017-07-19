
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productingorder;

import java.util.List;

public interface IProductingorderControl {
    public List<Productingorder> loadAllProductingorder();
    public void modifyProductingorder(Productingorder productingorder);
    public void addProductingorder(Productingorder productingorder);
    public void delProductingorder(Productingorder productingorder);
}
