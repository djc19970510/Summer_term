
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Production;

import java.util.List;

public interface IProductionControl {
    public List<Production> loadAllProduction();
    public void modifyProduction(Production production);
    public void addProduction(Production production);
    public void delProduction(Production production);
}
