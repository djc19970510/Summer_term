
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Materialsstoreorder;

import java.util.List;

public interface IMaterialsstoreorderControl {
    public List<Materialsstoreorder> loadAllMaterialsstoreorder();
    public void modifyMaterialsstoreorder(Materialsstoreorder materialsstoreorder);
    public void addMaterialsstoreorder(Materialsstoreorder materialsstoreorder);
    public void delMaterialsstoreorder(Materialsstoreorder materialsstoreorder);
}
