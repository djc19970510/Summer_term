
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Materialsorder;

import java.util.List;

public interface IMaterialsorderControl {
    public List<Materialsorder> loadAllMaterialsorder();
    public void modifyMaterialsorder(Materialsorder materialsorder);
    public void addMaterialsorder(Materialsorder materialsorder);
    public void delMaterialsorder(Materialsorder materialsorder);
}
