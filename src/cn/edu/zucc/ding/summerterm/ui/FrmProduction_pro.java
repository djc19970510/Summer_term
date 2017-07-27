package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.control.ProductionTypeControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiontype;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmProduction_pro extends JDialog implements ActionListener{
    private JLabel NameL =
            new JLabel("　　产品名");
    private JLabel PriceL =
            new JLabel("　　　价格");
    private JTextField NameT = new JTextField(15);
    private JTextField PriceT = new JTextField(15);
    private JComboBox type_chose;
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private List<Productiontype> tmp;
    private FrmProduction fp;
    private Production pro;

    public FrmProduction_pro(FrmProduction fp,Production pro){
        this.fp = fp;
        this.pro = pro;
        tmp = (new ProductionTypeControl()).loadAllProductiontype();
        Integer[] typeindex = new Integer[tmp.size()];
        String[] type = new String[tmp.size()];
        for (int i=0;i<tmp.size();i++){
            typeindex[i] = tmp.get(i).getID();
            type[i] = tmp.get(i).getName();
        }
        this.type_chose =  new JComboBox(type);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setSize(new Dimension(280,135));
        this.add(NameL);
        this.add(NameT);
        this.NameT.setText(this.pro!=null?this.pro.getName():"");
        this.add(PriceL);
        this.add(PriceT);
        this.PriceT.setText(this.pro!=null?String.valueOf(this.pro.getPrice()):"");
        this.add(type_chose);
        this.add(Add_OK);
        this.add(Add_Cancel);
        if(this.pro!=null){
            int j;
            for (j=0;j<typeindex.length;j++){
                if(this.pro.getProductionTypeID()==typeindex[j])
                    break;
            }
            this.type_chose.setSelectedIndex(j);
        }
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_OK){
            try {
                if(this.NameT.getText().equals("")){
                    throw new BaseException("未输入产品名称");
                }else if(!DatabaseOP.isDouble(this.PriceT.getText())){
                    throw new BaseException("产品价格不合法");
                }
            }catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            int i=type_chose.getSelectedIndex();
            Production p = new Production(
                pro!=null?pro.getID():0,this.NameT.getText(),Double.valueOf(this.PriceT.getText()),tmp.get(i).getID()
            );
            if (pro==null)
                (new ProductionControl()).addProduction(p);
            else
                (new ProductionControl()).modifyProduction(p);
            int sr = fp.table_productionType.getSelectedRow();
            if(sr<0)    return;
            Productiontype pt = fp.productiontypes.get(sr);
            fp.reloadAll(pt);
            fp.reloadAllDetails(new Production(-1,"",0,0));
            this.setVisible(false);
            this.removeAll();
        }
    }
}
