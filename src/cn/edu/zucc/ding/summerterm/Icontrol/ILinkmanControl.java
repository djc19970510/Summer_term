
package cn.edu.zucc.ding.summerterm.Icontrol;

import cn.edu.zucc.ding.summerterm.model.Linkman;

import java.util.List;

public interface ILinkmanControl {
    public List<Linkman> loadAllLinkman();
    public void modifyLinkman(Linkman linkman);
    public void addLinkman(Linkman linkman);
    public void delLinkman(Linkman linkman);
}
