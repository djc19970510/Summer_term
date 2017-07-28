package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.MaterialsOrderControl;
import cn.edu.zucc.ding.summerterm.control.MaterialsStoreControl;
import cn.edu.zucc.ding.summerterm.control.MaterialsStoreOrderControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.Materialsorder;
import cn.edu.zucc.ding.summerterm.model.Materialsstore;
import cn.edu.zucc.ding.summerterm.model.Materialsstoreorder;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class FrmOrder_buymaterials extends JDialog implements ActionListener {
    private JLabel materials = new JLabel("材料");
    private JComboBox materialsbox;
    private JLabel numberL = new JLabel("数量");
    private JTextField numberT = new JTextField(10);
    private JLabel priceL = new JLabel("价格");
    private JTextField priceT = new JTextField(10);
    private JLabel DateL = new JLabel("时间");
    private JTextField DateT = new JTextField(10);
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmOrder fo;
    private List<Materials> materialsl;

    public FrmOrder_buymaterials(FrmOrder fo){
        materialsl = (new MaterialsControl()).loadAllMaterials();
        String[] materstring = new String[materialsl.size()];
        for(int i=0;i<materialsl.size();i++){
            materstring[i] = materialsl.get(i).getName();
        }
        materialsbox = new JComboBox(materstring);
        materialsbox.setPreferredSize(new Dimension(110,20));
        this.fo = fo;
        this.setSize(new Dimension(180,200));
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.add(materials);
        this.add(materialsbox);
        this.add(numberL);
        this.add(numberT);
        this.add(priceL);
        this.add(priceT);
        this.add(DateL);
        this.add(DateT);
        DateT.setText(new Timestamp(System.currentTimeMillis()).toString());
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_Cancel.addActionListener(this);
        this.Add_OK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            int id=-1;
            int sr = materialsbox.getSelectedIndex();
            Materials m = materialsl.get(sr);
            Connection conn = null;
            String sql = "select id from materialsstore where MaterialsID=?";
            try {
                if(!DatabaseOP.isDouble(this.numberT.getText())||!DatabaseOP.isDouble(this.priceT.getText())){
                    throw new BaseException("数量或价格输入不合法");
                }
                conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,m.getID());
                ResultSet rs = pst.executeQuery();
                if(!rs.next()){throw new BaseException("该材料未设置存放仓库");}
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (BaseException e1){
                e1.printStackTrace();
                return;
            }

            Materialsorder mo = new Materialsorder(-1,Double.valueOf(priceT.getText()),Double.valueOf(numberT.getText()),m.getID(),Timestamp.valueOf(DateT.getText()));
            (new MaterialsOrderControl()).addMaterialsorder(mo);
            sql = "select id from materialsorder order by id desc";
            try {
                conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    id=rs.getInt(1);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            Materialsstoreorder mso = new Materialsstoreorder(-1,Double.valueOf(numberT.getText()),Timestamp.valueOf(DateT.getText()),m.getID(),id);
            System.out.println(id);
            (new MaterialsStoreOrderControl()).addMaterialsstoreorder(mso);
            sql = "update materialsstore set Number=Number+? where MaterialsID=?";
            try {
                conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setDouble(1,Double.valueOf(numberT.getText()));
                pst.setInt(2,m.getID());
                pst.execute();
                pst.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            this.setVisible(false);
            this.removeAll();
            fo.reloadm(null);
            fo.reloadp(null);
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }
}
