package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.CustomerControl;
import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmOrder extends JPanel implements ActionListener {
    private JPanel left = new JPanel();
    private JPanel center = new JPanel();
    private JPanel right = new JPanel();
    private JPanel left_top = new JPanel();
    private JPanel right_top = new JPanel();
    private JButton buy = new JButton("购入材料");
    private JButton sold = new JButton("卖出产品");
    private JButton sel_m = new JButton("查询");
    private JButton sel_p = new JButton("查询");
    private JTextField text_m = new JTextField(10);
    private JTextField text_p = new JTextField(10);

    private Object tblmTitle[] = {"序号", "材料名", "材料数量", "价格", "供应商", "日期"};
    private Object tblmData[][];
    DefaultTableModel tablmmod = new DefaultTableModel();
    List<MaterialsAndOrder> materialsAndOrders;
    private JTable Ordersm_infotable = new JTable(tablmmod);
    private JScrollPane Ordersm_infotableheader = new JScrollPane(Ordersm_infotable);

    private Object tblpTitle[] = {"序号", "产品名", "产品数量", "客户","价格", "日期"};
    private Object tblpData[][];
    DefaultTableModel tablpmod = new DefaultTableModel();
    List<ProductionsAndOrder> productionsAndOrders;
    private JTable Ordersp_infotable = new JTable(tablpmod);
    private JScrollPane Ordersp_infotableheader = new JScrollPane(Ordersp_infotable);

    private JList slist;
    private JList clist;
    List<Supplier> suppliers = (new SupplierControl()).loadAllSupplier();
    List<Customer> customers = (new CustomerControl()).loadAllCustomer();
    String[] sups;
    String[] cuss;

    public FrmOrder(){
        sups = new String[suppliers.size()];
        cuss = new String[customers.size()];
        for (int i=0;i<suppliers.size();i++){
            sups[i]=suppliers.get(i).getName();
        }
        for (int i=0;i<customers.size();i++){
            cuss[i]=customers.get(i).getName();
        }
        slist = new JList(sups);
        clist = new JList(cuss);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(left,BorderLayout.EAST);
            left.setLayout(new BorderLayout());
            left.add(Ordersp_infotableheader,BorderLayout.CENTER);
            left.add(left_top,BorderLayout.NORTH);
            left_top.setLayout(new FlowLayout());
            left_top.add(text_p);
            left_top.add(sel_p);
        this.add(center,BorderLayout.CENTER);
            center.add(buy);
            center.add(sold);
        this.add(right,BorderLayout.WEST);
            right.setLayout(new BorderLayout());
            right.add(Ordersm_infotableheader,BorderLayout.CENTER);
            right.add(right_top,BorderLayout.NORTH);
            right_top.setLayout(new FlowLayout());
            right_top.add(text_m);
            right_top.add(sel_m);
        this.reloadm(null);
        this.reloadp(null);
        this.buy.addActionListener(this);
        this.sold.addActionListener(this);
        this.sel_p.addActionListener(this);
        this.sel_m.addActionListener(this);
    }

    public void reloadp(String s){
        if(s==null||s.equals(""))
            productionsAndOrders = (new OtherControl()).loadAllProductionsAndOrder();
        else
            productionsAndOrders = (new OtherControl()).loadAllProductionsAndOrder(s);
        tblpData = new Object[productionsAndOrders.size()][6];
        for (int i = 0; i < productionsAndOrders.size(); i++) {
            tblpData[i][0] = i+1 + "";
            tblpData[i][1] = productionsAndOrders.get(i).getProductionname();
            tblpData[i][2] = productionsAndOrders.get(i).getNumber();
            tblpData[i][4] = productionsAndOrders.get(i).getPrice();
            tblpData[i][3] = productionsAndOrders.get(i).getCustomername();
            tblpData[i][5] = productionsAndOrders.get(i).getDate();
        }
        tablpmod.setDataVector(tblpData, tblpTitle);
        this.Ordersp_infotable.validate();
        this.Ordersp_infotable.repaint();
    }

    public void reloadm(String s){
        if(s==null||s.equals(""))
            materialsAndOrders = (new OtherControl()).loadAllMaterialsAndOrder();
        else
            materialsAndOrders = (new OtherControl()).loadAllMaterialsAndOrder(s);
        tblmData = new Object[materialsAndOrders.size()][6];
        for (int i = 0; i < materialsAndOrders.size(); i++) {
            tblmData[i][0] = i+1 + "";
            tblmData[i][1] = materialsAndOrders.get(i).getMaterialsName();
            tblmData[i][2] = materialsAndOrders.get(i).getNumber();
            tblmData[i][3] = materialsAndOrders.get(i).getPrice();
            tblmData[i][4] = materialsAndOrders.get(i).getSupplierName();
            tblmData[i][5] = materialsAndOrders.get(i).getDate();
        }
        tablmmod.setDataVector(tblmData, tblmTitle);
        this.Ordersm_infotable.validate();
        this.Ordersm_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.buy){
            FrmOrder_buymaterials dlg = new FrmOrder_buymaterials(this);
        }else if(e.getSource()==this.sold){
            FrmOrder_soldmaterials dlg = new FrmOrder_soldmaterials(this);
        }else if(e.getSource()==this.sel_m){
            this.reloadm(this.text_m.getText());
        }else if(e.getSource()==this.sel_p){
            this.reloadp(this.text_p.getText());
        }
    }
}
