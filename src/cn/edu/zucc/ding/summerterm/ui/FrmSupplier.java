package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmSupplier extends JPanel implements ActionListener {
    private JPanel task = new JPanel();
    private JPanel tablepanel = new JPanel();
    private JButton Supplier_add = new JButton("增加");
    private JButton Supplier_del = new JButton("删除");
    private JButton Supplier_mod = new JButton("修改");
    private JButton Supplier_sel = new JButton("查询");
    private JTextField Supplier_selectText = new JTextField(15);
    private Object tblTitle[] = {"序号","供应商编号", "供应商姓名", "供应商地址", "供应商描述", "联系人姓名", "联系人电话"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    List<Supplier> suppliers;
    private JTable Supplier_infotable = new JTable(tablmod);
    private JScrollPane Supplier_infotableheader = new JScrollPane(Supplier_infotable);


    public FrmSupplier() {
        this.setLayout(new BorderLayout());

        this.add(tablepanel, BorderLayout.CENTER);
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBackground(new Color(77, 199, 77));
        tablepanel.add(Supplier_infotableheader, BorderLayout.CENTER);


        Supplier_add.addActionListener(this);
        Supplier_mod.addActionListener(this);
        Supplier_del.addActionListener(this);
        Supplier_sel.addActionListener(this);

        this.add(task, BorderLayout.SOUTH);
        task.setBackground(new Color(77, 77, 77));
        task.add(Supplier_add);
        task.add(Supplier_del);
        task.add(Supplier_mod);
        task.add(Supplier_selectText);
        this.setVisible(true);
        task.add(Supplier_sel);
        this.setBackground(new Color(0, 255, 255));
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        if(s==null||s.equals(""))
            suppliers = (new SupplierControl()).loadAllSupplier();
        else
            suppliers = (new SupplierControl()).loadSomeSupplier(s);
        tblData = new Object[suppliers.size()][7];
        for (int i = 0; i < suppliers.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = suppliers.get(i).getID();
            tblData[i][2] = suppliers.get(i).getName();
            tblData[i][3] = suppliers.get(i).getAddress();
            tblData[i][5] = suppliers.get(i).getLinkName();
            tblData[i][4] = suppliers.get(i).getIntroduction();
            tblData[i][6] = suppliers.get(i).getLinkPhone();
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Supplier_infotable.validate();
        this.Supplier_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Supplier_add) {
            FrmSupplier_Add dlg = new FrmSupplier_Add(this);
            dlg.setVisible(true);
        } else if (e.getSource() == this.Supplier_del) {
            int sr = this.Supplier_infotable.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请选择要删除的供应商","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            SupplierControl sc = new SupplierControl();
            sc.delSupplier(suppliers.get(sr));
            this.reloadTable(null);
        } else if (e.getSource() == this.Supplier_sel) {
            String s = this.Supplier_selectText.getText();
            this.reloadTable(s);
        } else if (e.getSource() == this.Supplier_mod) {
            int i=this.Supplier_infotable.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择供应商","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Supplier su = this.suppliers.get(i);
            System.out.println(su.getID());
            FrmSupplier_Mod dlg = new FrmSupplier_Mod(this,su);
            dlg.setVisible(true);
        }
    }
}
