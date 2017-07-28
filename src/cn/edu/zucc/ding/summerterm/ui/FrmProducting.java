package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.control.ProductingOrderControl;
import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.model.OrderAndProduction;
import cn.edu.zucc.ding.summerterm.model.Productingorder;
import cn.edu.zucc.ding.summerterm.model.Production;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmProducting extends JPanel implements ActionListener {
    private JPanel task = new JPanel();
    private JPanel tablepanel = new JPanel();
    private JButton Producting_add = new JButton("生产");
    private JButton Producting_sel = new JButton("查询");
    private JTextField Producting_selectText = new JTextField(15);
    private Object tblTitle[] = {"序号","生产订单","产品名", "生产数量", "时间"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    List<OrderAndProduction> productingorders;
    private JTable Producting_infotable = new JTable(tablmod);
    private JScrollPane Producting_infotableheader = new JScrollPane(Producting_infotable);

    public FrmProducting(){
        this.setLayout(new BorderLayout());

        this.add(tablepanel, BorderLayout.CENTER);
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBackground(new Color(77, 199, 77));
        tablepanel.add(Producting_infotableheader, BorderLayout.CENTER);


        Producting_add.addActionListener(this);
        Producting_sel.addActionListener(this);

        this.add(task, BorderLayout.SOUTH);
        task.add(Producting_add);
        task.add(Producting_selectText);
        this.setVisible(true);
        task.add(Producting_sel);
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        if(s==null)
            productingorders = (new OtherControl()).loadAllProductingorder();
        //else
            //productingorders = (new ProductingOrderControl()).loadSomeCustomer(s);
        tblData = new Object[productingorders.size()][5];
        for (int i = 0; i < productingorders.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = productingorders.get(i).getID();
            tblData[i][2] = productingorders.get(i).getName();
            tblData[i][3] = productingorders.get(i).getProductionNumber();
            tblData[i][4] = productingorders.get(i).getDate();
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Producting_infotable.validate();
        this.Producting_infotable.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Producting_add){
            FrmProducting_add dlg = new FrmProducting_add(this);
        }
    }



}
