
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Productionstoreorder;

import java.util.List;

public interface IProductionstoreorderControl {
    public List<Productionstoreorder> loadAllProductionstoreorder();
    public void modifyProductionstoreorder(Productionstoreorder productionstoreorder);
    public void addProductionstoreorder(Productionstoreorder productionstoreorder);
    public void delProductionstoreorder(Productionstoreorder productionstoreorder);
}
