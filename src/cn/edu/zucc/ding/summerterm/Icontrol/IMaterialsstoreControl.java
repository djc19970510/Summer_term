
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Materialsstore;

import java.util.List;

public interface IMaterialsstoreControl {
    public List<Materialsstore> loadAllMaterialsstore();
    public void modifyMaterialsstore(Materialsstore materialsstore);
    public void addMaterialsstore(Materialsstore materialsstore);
    public void delMaterialsstore(Materialsstore materialsstore);
}
