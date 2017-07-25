package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndSuppliers;
import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FrmMaterials extends JPanel implements ActionListener{
    private JPanel task = new JPanel();
    private JPanel tablepanel = new JPanel();
    private JButton Materials_add = new JButton("增加");
    private JButton Materials_del = new JButton("删除");
    private JButton Materials_mod = new JButton("修改");
    private JButton Materials_sel = new JButton("查询");
    private JTextField Materials_selectText = new JTextField(15);
    private Object tblTitle[] = {"材料序号", "材料名称", "材料价格", "材料介绍", "生产厂商"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    List<MaterialsAndSuppliers> materialsAndSuppliers;
    private JTable Materials_infotable = new JTable(tablmod);
    private JScrollPane Materials_infotableheader = new JScrollPane(Materials_infotable);

    public FrmMaterials(){
        this.setLayout(new BorderLayout());

        this.add(tablepanel, BorderLayout.CENTER);
        tablepanel.setLayout(new BorderLayout());
        tablepanel.setBackground(new Color(77, 199, 77));
        tablepanel.add(Materials_infotableheader, BorderLayout.CENTER);


        Materials_add.addActionListener(this);
        Materials_mod.addActionListener(this);
        Materials_del.addActionListener(this);
        Materials_sel.addActionListener(this);

        this.add(task, BorderLayout.SOUTH);
        task.setBackground(new Color(77, 77, 77));
        task.add(Materials_add);
        task.add(Materials_del);
        task.add(Materials_mod);
        task.add(Materials_selectText);
        this.setVisible(true);
        task.add(Materials_sel);
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        if(s==null)
            materialsAndSuppliers = (new OtherControl()).loadSomeMaterialsAndSupplier();
        else
            materialsAndSuppliers = (new OtherControl()).loadSomeMaterialsAndSupplier(s);
        tblData = new Object[materialsAndSuppliers.size()][5];
        for (int i = 0; i < materialsAndSuppliers.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = materialsAndSuppliers.get(i).getName();
            tblData[i][2] = materialsAndSuppliers.get(i).getMaterialsBasePrice();
            tblData[i][3] = materialsAndSuppliers.get(i).getIntroduction();
            tblData[i][4] = materialsAndSuppliers.get(i).getSupplierName();
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Materials_infotable.validate();
        this.Materials_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.Materials_add) {
            FrmMaterials_Add dlg = new FrmMaterials_Add(this,null);
            dlg.setVisible(true);
        } else if (e.getSource() == this.Materials_del){
            int i=this.Materials_infotable.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择删除的材料","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String sql = "select * from productiondetails where MaterialsID=?";
            MaterialsAndSuppliers mas = this.materialsAndSuppliers.get(i);
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,mas.getID());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,  "无法删除，有产品使用此材料","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Materials m = new Materials();
            m.setID(mas.getID());
            (new MaterialsControl()).delMaterials(m);
            this.reloadTable(null);
        } else if (e.getSource() == this.Materials_sel) {
            String s = this.Materials_selectText.getText();
            this.reloadTable(s);
        } else if (e.getSource() == this.Materials_mod) {
            int i=this.Materials_infotable.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择材料","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            MaterialsAndSuppliers mas = this.materialsAndSuppliers.get(i);
            FrmMaterials_Add dlg = new FrmMaterials_Add(this,mas);
            dlg.setVisible(true);
            this.reloadTable(null);
        }
    }
}
