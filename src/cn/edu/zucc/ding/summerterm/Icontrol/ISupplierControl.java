package cn.edu.zucc.ding.summerterm.Icontrol;



import cn.edu.zucc.ding.summerterm.model.Supplier;

import java.util.List;

public interface ISupplierControl {
    public List<Supplier> loadAllSupplier();
    public void modifySupplier(Supplier supplier);
    public void addSupplier(Supplier supplier);
    public void delSupplier(Supplier supplier);
}
