package cn.edu.zucc.ding.summerterm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmMain extends JFrame implements ActionListener{
    private JMenuBar menubar = new JMenuBar();
    private JPanel tmp;
    private JPanel centerPanel = new JPanel();
    private JButton menu_Production=new JButton("产品管理");
    private JButton menu_Supplier=new JButton("供应商管理");
    private JButton menu_Customer=new JButton("客户管理");
    private JButton menu_Store=new JButton("仓库管理");
    private JButton menu_Order=new JButton("订单管理");
    private JButton menu_Producting=new JButton("生产管理");
    private JButton menu_Linkman=new JButton("材料管理");



    public FrmMain(){
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("生产管理系统");
        menu_Supplier.setBackground(this.getBackground());
        menubar.add(menu_Production);
        menu_Production.addActionListener(this);
        menubar.add(menu_Linkman);
        menu_Linkman.addActionListener(this);
        menubar.add(menu_Supplier);
        menu_Supplier.addActionListener(this);
        menubar.add(menu_Customer);
        menu_Customer.addActionListener(this);
        menubar.add(menu_Store);
        menu_Store.addActionListener(this);
        menubar.add(menu_Order);
        menu_Order.addActionListener(this);
        menubar.add(menu_Producting);
        menu_Producting.addActionListener(this);

        this.getContentPane().add(menubar,BorderLayout.NORTH);
        this.getContentPane().add(centerPanel,BorderLayout.CENTER);
        centerPanel.setBackground(new Color(255,255,255));
        centerPanel.setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.setVisible(true);
    }


    public static void main(String[] args){
        new FrmMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tmp!=null){
            tmp.setVisible(false);
            tmp.removeAll();
        }
        if(e.getSource()==this.menu_Supplier){
            tmp=new FrmSupplier();
        }else if (e.getSource()==this.menu_Customer){
            tmp=new FrmCustomer();
        }else if (e.getSource()==this.menu_Linkman){
            tmp=new FrmMaterials();
        }else if (e.getSource()==this.menu_Order){
            tmp=new FrmOrder();
        }else if (e.getSource()==this.menu_Producting){
            tmp=new FrmProducting();
        }else if (e.getSource()==this.menu_Production){
            tmp=new FrmProduction();
        }else if (e.getSource()==this.menu_Store){
            tmp=new FrmStore();
        }
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);
        centerPanel.add(tmp,BorderLayout.CENTER);
        tmp.setVisible(true);
    }
}
