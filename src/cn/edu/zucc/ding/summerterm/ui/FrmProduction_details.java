package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.ProductionDetailControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndDetails;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiondetails;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import sun.security.util.Resources_es;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FrmProduction_details extends JDialog implements ActionListener {
    private List<Materials> materials;
    private JLabel NameL =
            new JLabel("材料名称");
    private JLabel NumberL =
            new JLabel("材料数量");
    private JComboBox NameT;
    private JTextField NumberT = new JTextField(15);
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmProduction fp;
    private MaterialsAndDetails prod;
    private Production pt;
    private int id1;
    public FrmProduction_details(FrmProduction fp,MaterialsAndDetails prod){
        this.materials = (new MaterialsControl()).loadAllMaterials();
        try {
            if(materials.size()<=0){
                throw new BaseException("没有材料信息，请先输入材料");
            }
        }catch (BaseException e1){
            e1.printStackTrace();
            return;
        }
        String[] materialsstring = new String[materials.size()];
        for(int i=0;i<materials.size();i++){
            materialsstring[i] = materials.get(i).getName();
        }
        NameT = new JComboBox(materialsstring);
        NameT.setPreferredSize(new Dimension(170,20));
        this.fp = fp;
        this.prod = prod;
        this.setLayout(new FlowLayout());
        this.add(NameL);
        this.add(NameT);
        int t=0;
        if(prod!=null){
            for(int i=0;i<materials.size();i++){
                if(prod.getName().equals(materials.get(i).getName()))
                    t=i;
            }
        }
        this.NameT.setSelectedIndex(t);
        this.add(NumberL);
        this.add(NumberT);
        this.NumberT.setText(prod==null?"":prod.getNumber().toString());
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setVisible(true);
        this.setSize(new Dimension(300,135));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_OK){
            if(!DatabaseOP.isDouble(this.NumberT.getText())){
                try {
                    throw new BaseException("数量输入不合法");
                } catch (BaseException e1) {
                    e1.printStackTrace();
                    return;
                }
            }
            int sr = fp.table_production.getSelectedRow();
            Production pro = fp.productions.get(sr);
            int src = this.NameT.getSelectedIndex();
            String sql = "select ID from materials where name='"+materials.get(src).getName()+"'";
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    id1 = rs.getInt(1);
                }else{
                    throw new BaseException("不存在此类材料");
                }
                System.out.println(pro.getID());
                System.out.println(materials.get(src).getID());
                sql = "select ID from productiondetails where ProductionID='"+pro.getID()+"' and MaterialsID='"+materials.get(src).getID()+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if(rs.next()){
                    rs.close();
                    pst.close();
                    conn.close();
                    throw new BaseException("产品细节中已有该材料信息");
                }
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            Productiondetails pd = new Productiondetails(
                prod==null?-1:prod.getID(),
                pro.getID(),
                id1,
                Double.valueOf(this.NumberT.getText())
            );
            ProductionDetailControl pdc = new ProductionDetailControl();
            if(prod==null){
                pdc.addProductiondetails(pd);
            }else {
                pdc.modifyProductiondetails(pd);
            }
            fp.reloadAllDetails(pro);
            this.setVisible(false);
            this.removeAll();
        }
    }
}
