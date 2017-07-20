package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmSupplier extends JPanel implements ActionListener{
    private JPanel task = new JPanel();
    private JPanel tablepanel = new JPanel();
    private JButton Supplier_add = new JButton("增加");
    private JTextField Supplier_selectText = new JTextField(15);
    private JButton Supplier_del = new JButton("删除");
    private JButton Supplier_mod = new JButton("修改");
    private JButton Supplier_sel = new JButton("查询");
    private JButton Supplier_before = new JButton("上一页");
    private JButton Supplier_next = new JButton("下一页");
    private Object tblTitle[]={"供应商ID","供应商姓名","供应商地址","供应商描述","联系人姓名","联系人电话"};
    private Object tblData[][] ;
    DefaultTableModel tablmod=new DefaultTableModel();
    private JTable Supplier_infotable = new JTable(tablmod);
    private JScrollPane Supplier_infotableheader = new JScrollPane(Supplier_infotable);

    private JLabel test = new JLabel("123");
    public FrmSupplier(){
        this.setLayout(new BorderLayout());

        this.add(tablepanel,BorderLayout.CENTER);
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBackground(new Color(77,199,77));
        tablepanel.add(Supplier_infotableheader,BorderLayout.CENTER);



        this.add(task,BorderLayout.SOUTH);
        task.setBackground(new Color(77,77,77));
        task.add(Supplier_add);
        task.add(Supplier_del);
        task.add(Supplier_mod);
        task.add(Supplier_selectText);
        task.add(Supplier_sel);
        task.add(Supplier_before);
        task.add(Supplier_next);
        this.setVisible(true);
        this.setBackground(new Color(0,255,255));
        this.reloadTable();
    }

    private void reloadTable(){
        tblData= new Object[][]{
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
                {"row1-col1", "row1-col2", "row1-col4", "row1-col5", "row1-col3", "row1-col3"},
        };
        tablmod.setDataVector(tblData,tblTitle);
        this.Supplier_infotable.validate();
        this.Supplier_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
