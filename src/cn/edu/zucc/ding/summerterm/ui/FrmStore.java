package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndStore;
import cn.edu.zucc.ding.summerterm.model.ProductionAndStore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmStore extends JPanel implements ActionListener{
    private JPanel left = new JPanel();
        private JPanel left_top= new JPanel();
            private Object tblTitle[] = {"序号", "材料名称", "材料存放数量", "存放地址","供应商"};
            private Object tblData[][];
            DefaultTableModel materials_tablmod = new DefaultTableModel();
            List<MaterialsAndStore> materialsAndStore;
            private JTable Materials_infotable = new JTable(materials_tablmod);
            private JScrollPane Materials_infotableheader = new JScrollPane(Materials_infotable);
        private JPanel left_mid= new JPanel();
            private JButton Add_m = new JButton("增加");
            private JButton Del_m = new JButton("修改");
            private JTextField sel_m_text = new JTextField(15);
            private JButton Sel_m = new JButton("查询");
            private JButton order_m = new JButton("出入库情况");
    private JPanel right = new JPanel();
        private JPanel right_top = new JPanel();
            private Object tblPTitle[] = {"序号", "产品名称", "产品存放数量", "存放地址"};
            private Object tblPData[][];
            DefaultTableModel production_tablmod = new DefaultTableModel();
            List<ProductionAndStore> ProductionAndStores;
            private JTable Productions_infotable = new JTable(production_tablmod);
            private JScrollPane Productions_infotableheader = new JScrollPane(Productions_infotable);
        private JPanel right_mid= new JPanel();
            private JButton Add_p = new JButton("增加");
            private JButton Del_p = new JButton("修改");
            private JTextField sel_p_text = new JTextField(15);
            private JButton Sel_p = new JButton("查询");
            private JButton order_p = new JButton("出入库情况");


    public FrmStore(){
        left = new JPanel();
        left.setPreferredSize(new Dimension(800,900));
        right = new JPanel();
        right.setPreferredSize(new Dimension(800,900));
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        this.add(jsp);
        setVisible(true);
        jsp.setDividerLocation(0.5);// 在1/2处进行拆分

        left.setLayout(new BorderLayout());
        left.add(left_top,BorderLayout.NORTH);
        left.add(left_mid,BorderLayout.CENTER);
        left_top.setLayout(new FlowLayout());
        left_top.add(Add_m);
        left_top.add(Del_m);
        left_top.add(sel_m_text);
        left_top.add(Sel_m);
        left_top.add(order_m);
        this.Add_m.addActionListener(this);
        this.Del_m.addActionListener(this);
        this.Sel_m.addActionListener(this);
        this.order_m.addActionListener(this);
        left_mid.add(Materials_infotableheader);

        right.setLayout(new BorderLayout());
        right.add(right_top,BorderLayout.NORTH);
        right.add(right_mid,BorderLayout.CENTER);
        right_top.setLayout(new FlowLayout());
        right_top.add(Add_p);
        right_top.add(Del_p);
        right_top.add(sel_p_text);
        right_top.add(Sel_p);
        right_top.add(order_p);
        this.Add_p.addActionListener(this);
        this.Del_p.addActionListener(this);
        this.Sel_p.addActionListener(this);
        this.order_p.addActionListener(this);
        right_mid.add(Productions_infotableheader);
        this.reloadmTable(null);
        this.reloadpTable(null);
    }

    protected void reloadmTable(String s) {
        if(s==null)
            materialsAndStore = (new OtherControl()).loadAllMaterialsAndStore();
        else{
            materialsAndStore = (new OtherControl()).loadAllMaterialsAndStore(s);
        }
        //materialsAndStore = (new OtherControl()).loadSomeMaterialsAndSupplier(s);
        tblData = new Object[materialsAndStore.size()][5];
        for (int i = 0; i < materialsAndStore.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = materialsAndStore.get(i).getName();
            tblData[i][2] = materialsAndStore.get(i).getNumber();
            tblData[i][3] = materialsAndStore.get(i).getAddress();
            tblData[i][4] = materialsAndStore.get(i).getSupplierName();
        }
        materials_tablmod.setDataVector(tblData, tblTitle);
        this.Materials_infotable.validate();
        this.Materials_infotable.repaint();
    }

    protected void reloadpTable(String s) {
        if(s==null)
            ProductionAndStores = (new OtherControl()).loadAllProductionsAndStores();
        else{
            ProductionAndStores = (new OtherControl()).loadAllProductionsAndStores(s);
        }
        //materialsAndStore = (new OtherControl()).loadSomeMaterialsAndSupplier(s);
        tblPData = new Object[ProductionAndStores.size()][5];
        for (int i = 0; i < ProductionAndStores.size(); i++) {
            tblPData[i][0] = i+1 + "";
            tblPData[i][1] = ProductionAndStores.get(i).getName();
            tblPData[i][2] = ProductionAndStores.get(i).getNumber();
            tblPData[i][3] = ProductionAndStores.get(i).getAddress();
        }
        production_tablmod.setDataVector(tblPData, tblPTitle);
        this.Productions_infotable.validate();
        this.Productions_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Sel_m){
            String s = this.sel_m_text.getText();
            this.reloadmTable(s);
        }else if(e.getSource()==this.Sel_p){
            String s = this.sel_p_text.getText();
            this.reloadpTable(s);
        }else if(e.getSource()==this.Del_m){
            int sr = Materials_infotable.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请选择要修改的材料仓库信息","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            MaterialsAndStore ms = materialsAndStore.get(sr);
            FrmStore_addm dlg = new FrmStore_addm(this,ms);
        }else if(e.getSource()==this.Del_p){
            int sr =Productions_infotable.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请选择要修改的产品仓库信息","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            ProductionAndStore ms = ProductionAndStores.get(sr);
            FrmStore_addp dlg = new FrmStore_addp(this,ms);
        }else if(e.getSource()==this.Add_m){
            FrmStore_addm dlg = new FrmStore_addm(this);
        }else if(e.getSource()==this.Add_p){
            FrmStore_addp dlg = new FrmStore_addp(this);
        }else if(e.getSource()==this.order_m){
            FrmStore_orderm dlg = new FrmStore_orderm();
        }else if(e.getSource()==this.order_p){
            FrmStore_orderp dlg = new FrmStore_orderp();
        }
    }
}
