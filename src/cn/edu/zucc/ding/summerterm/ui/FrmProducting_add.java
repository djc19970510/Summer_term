package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.model.Productingorder;
import cn.edu.zucc.ding.summerterm.model.Production;
import hong.yelinggu.date.HongYeLingGuDate;
import hong.yelinggu.date.absinterface.SelectHYDateAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmProducting_add extends JDialog implements ActionListener {
    private JLabel productionL = new JLabel("产品");
    private JComboBox productionbox;
    private List<Production> productions = new ArrayList<Production>();
    private String[] productionsString;
    private JLabel numberL = new JLabel("数量");
    private JTextField numberT = new JTextField(10);
    private JLabel timeL = new JLabel("时间");
    private JTextField timeT = new JTextField(10);
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private static String s = "yyyy-MM-dd HH:mm:ss";
    private FrmProducting fp;

    public FrmProducting_add(FrmProducting fp){
        this.fp = fp;
        this.setSize(new Dimension(180,200));
        this.setVisible(true);
        productions = (new ProductionControl()).loadAllProduction();
        productionsString = new String[productions.size()];
        for(int i=0;i<productions.size();i++){
            productionsString[i] = productions.get(i).getName();
        }
        this.productionbox = new JComboBox(productionsString);
        this.productionbox.setPreferredSize(new Dimension(110,20));
        this.setLayout(new FlowLayout());
        this.add(productionL);
        this.add(productionbox);
        this.add(numberL);
        this.add(numberT);
        this.add(timeL);
        this.add(timeT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            int sr = productionbox.getSelectedIndex();

            Productingorder po = new Productingorder(
                      -1,productions.get(sr).getID(),Double.valueOf(this.numberT.getText()), Timestamp.valueOf(this.timeT.getText())
            );
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }
}
