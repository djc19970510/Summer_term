package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.CustomerControl;
import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.Customer;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FrmCustomer extends JPanel implements ActionListener{
    private JPanel task = new JPanel();
    private JPanel tablepanel = new JPanel();
    private JButton Customer_add = new JButton("增加");
    private JButton Customer_del = new JButton("删除");
    private JButton Customer_mod = new JButton("修改");
    private JButton Customer_sel = new JButton("查询");
    private JTextField Customer_selectText = new JTextField(15);
    private Object tblTitle[] = {"序号","客户编号","客户姓名", "客户地址", "联系人姓名", "联系人电话"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    List<Customer> customers;
    private JTable Customer_infotable = new JTable(tablmod);
    private JScrollPane Customer_infotableheader = new JScrollPane(Customer_infotable);

    public FrmCustomer(){
        this.setLayout(new BorderLayout());

        this.add(tablepanel, BorderLayout.CENTER);
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBackground(new Color(77, 199, 77));
        tablepanel.add(Customer_infotableheader, BorderLayout.CENTER);


        Customer_add.addActionListener(this);
        Customer_mod.addActionListener(this);
        Customer_del.addActionListener(this);
        Customer_sel.addActionListener(this);

        this.add(task, BorderLayout.SOUTH);
        task.setBackground(new Color(77, 77, 77));
        task.add(Customer_add);
        task.add(Customer_del);
        task.add(Customer_mod);
        task.add(Customer_selectText);
        this.setVisible(true);
        task.add(Customer_sel);
        this.setBackground(new Color(0, 255, 255));
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        if(s==null||s.equals(""))
            customers = (new CustomerControl()).loadAllCustomer();
        else
            customers = (new CustomerControl()).loadSomeCustomer(s);
        tblData = new Object[customers.size()][6];
        for (int i = 0; i < customers.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = customers.get(i).getID();
            tblData[i][2] = customers.get(i).getName();
            tblData[i][3] = customers.get(i).getAddress();
            tblData[i][4] = customers.get(i).getLinkName();
            tblData[i][5] = customers.get(i).getLinkPhone();
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Customer_infotable.validate();
        this.Customer_infotable.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Customer_add) {
            FrmCustomer_Add dlg = new FrmCustomer_Add(this);
            dlg.setVisible(true);
        } else if (e.getSource() == this.Customer_del) {
            int i=this.Customer_infotable.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择删除的客户","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Customer cu = this.customers.get(i);
            try {
                Connection conn = DBUtil.getConnection();
                String sql = "select * from productionorder where CustomerID="+cu.getID();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    throw new BaseException("系统中存在改客户订单，无法删除");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            (new CustomerControl()).delCustomer(cu);
            this.reloadTable(null);
        } else if (e.getSource() == this.Customer_sel) {
            String s = this.Customer_selectText.getText();
            this.reloadTable(s);
        } else if (e.getSource() == this.Customer_mod) {
            int i=this.Customer_infotable.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择客户","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Customer cu = this.customers.get(i);
            FrmCustomer_Mod dlg = new FrmCustomer_Mod(this,cu);
            dlg.setVisible(true);
        }
    }
}
