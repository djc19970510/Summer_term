package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndSuppliers;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import cn.edu.zucc.ding.summerterm.util.DbException;

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
    private JLabel pricetolabel = new JLabel("价格区间");
    private JTextField lowprice = new JTextField(2);
    private JTextField highprice = new JTextField(2);
    private JLabel tolabel = new JLabel("-");
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
        task.setBackground(new Color(188, 188, 188));
        task.add(Materials_add);
        task.add(Materials_del);
        task.add(Materials_mod);
        task.add(Materials_selectText);
        this.setVisible(true);
        task.add(Materials_sel);
        task.add(pricetolabel);
        task.add(lowprice);
        task.add(tolabel);
        task.add(highprice);

        this.reloadTable(null,null,null);
    }

    protected void reloadTable(String s,String low,String high) {
        double low1 = 0;
        double high1 = Double.MAX_VALUE;
        if(!(low==null||low.equals("")))
            low1 = Double.valueOf(low);
        if(!(high==null||high.equals("")))
            high1 = Double.valueOf(high);
        if(s==null)
            materialsAndSuppliers = (new OtherControl()).loadSomeMaterialsAndSupplier();
        else
            materialsAndSuppliers = (new OtherControl()).loadSomeMaterialsAndSupplier(s,low1,high1);
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
                try {
                    throw new BaseException("请选择删除的材料");
                } catch (BaseException e1) {
                    e1.printStackTrace();
                }
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
                    throw new BaseException("无法删除，有产品使用此材料");
                }
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    throw new DbException(e1);
                } catch (DbException e2) {
                    e2.printStackTrace();
                }
            }catch (BaseException e2){
                e2.printStackTrace();
            }
            Materials m = new Materials();
            m.setID(mas.getID());
            (new MaterialsControl()).delMaterials(m);
            this.reloadTable(null,null,null);
        } else if (e.getSource() == this.Materials_sel) {
            String s = this.Materials_selectText.getText();
            try {
                if(!((DatabaseOP.isDouble(this.lowprice.getText())||this.lowprice.getText().equals(""))&&(DatabaseOP.isDouble(this.highprice.getText())||this.highprice.getText().equals(""))))
                    throw new BaseException("价格区间输入非法");
            }catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            this.reloadTable(s,lowprice.getText(),highprice.getText());
        } else if (e.getSource() == this.Materials_mod) {
            int i=this.Materials_infotable.getSelectedRow();
            if(i<0){
                try {
                    throw new BaseException("请选择材料");
                } catch (BaseException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            MaterialsAndSuppliers mas = this.materialsAndSuppliers.get(i);
            FrmMaterials_Add dlg = new FrmMaterials_Add(this,mas);
            dlg.setVisible(true);
            this.reloadTable(null,null,null);
        }
    }
}
