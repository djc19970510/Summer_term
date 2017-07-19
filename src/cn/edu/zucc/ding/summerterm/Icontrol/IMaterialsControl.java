
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Materials;

import java.util.List;

public interface IMaterialsControl {
    public List<Materials> loadAllMaterials();
    public void modifyMaterials(Materials materials);
    public void addMaterials(Materials materials);
    public void delMaterials(Materials materials);
}
