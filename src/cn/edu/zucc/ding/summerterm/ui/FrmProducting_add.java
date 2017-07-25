package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.*;
import cn.edu.zucc.ding.summerterm.model.*;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import hong.yelinggu.date.HongYeLingGuDate;
import hong.yelinggu.date.absinterface.SelectHYDateAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmProducting_add extends JDialog implements ActionListener {
    private JLabel productionL = new JLabel("产品");
    private JComboBox productionbox;
    private List<Production> productions = new ArrayList<Production>();
    private String[] productionsString;
    private JLabel numberL = new JLabel("数量");
    private JTextField numberT = new JTextField(10);
    private JLabel timeL = new JLabel("时间");
    private JTextField timeT = new JTextField(10);
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private static String s = "yyyy-MM-dd HH:mm:ss";
    private FrmProducting fp;

    public FrmProducting_add(FrmProducting fp){
        this.fp = fp;
        this.setSize(new Dimension(180,200));
        this.setVisible(true);
        productions = (new ProductionControl()).loadAllProduction();
        productionsString = new String[productions.size()];
        for(int i=0;i<productions.size();i++){
            productionsString[i] = productions.get(i).getName();
        }
        this.productionbox = new JComboBox(productionsString);
        this.productionbox.setPreferredSize(new Dimension(110,20));
        this.setLayout(new FlowLayout());
        this.add(productionL);
        this.add(productionbox);
        this.add(numberL);
        this.add(numberT);
        this.add(timeL);
        this.add(timeT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            int sr = productionbox.getSelectedIndex();
            int id = productions.get(sr).getID();
            double number = Double.valueOf(this.numberT.getText());
            if(examMaterial(id,number)){
                Productingorder po = new Productingorder(
                        -1,id,number, Timestamp.valueOf(this.timeT.getText())
                );
                (new ProductingOrderControl()).addProductingorder(po);
                try {
                    Connection conn = DBUtil.getConnection();
                    String sql = "select id from productingorder where ProductionID=? and ProductionNumber=? and Date=?";
                    PreparedStatement pst=conn.prepareStatement(sql);
                    pst.setInt(1,id);
                    pst.setDouble(2,number);
                    pst.setTimestamp(3,Timestamp.valueOf(this.timeT.getText()));
                    ResultSet rs = pst.executeQuery();
                    int orderid;
                    if(rs.next()){
                        orderid = rs.getInt(1);
                        Materialsout(id,number,orderid,Timestamp.valueOf(this.timeT.getText()));
                        Productionin(id,number,orderid,Timestamp.valueOf(this.timeT.getText()));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


            this.setVisible(false);
            this.removeAll();
            fp.reloadTable(null);
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }

    private boolean examMaterial(int id,double number){
        String sql = "SELECT MaterialsNumber,Number FROM mydb.productiondetails,materialsstore where productiondetails.MaterialsID=materialsstore.MaterialsID and Productionid="+id;
        try {
            Connection conn=DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getDouble(1)*number>rs.getDouble(2)) {
                    JOptionPane.showMessageDialog(null, "库存不足", "提示", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void Materialsout(int proid,double number,int orderid,Timestamp time){
        try {
            List<Productiondetails> result = new ArrayList<Productiondetails>();
            Connection conn = DBUtil.getConnection();
            String sql = "select * from productiondetails where ProductionID="+proid;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                result.add(
                new Productiondetails(
                        rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4)
                ));
            }
            for(int i=0;i<result.size();i++){
                (new MaterialsStoreOrderControl()).addMaterialsstoreorder(new Materialsstoreorder(-1,result.get(i).getMaterialsNumber()*number*-1,
                        time,result.get(i).getMaterialsID(),orderid));
                sql = "update materialsstore set Number=Number-? where MaterialsID=?";
                pst = conn.prepareStatement(sql);
                pst.setDouble(1,result.get(i).getMaterialsNumber()*number);
                pst.setInt(2,result.get(i).getMaterialsID());
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void Productionin(int proid,double number,int orderid,Timestamp time){
        try {
            int storeid;
            Connection conn = DBUtil.getConnection();
            String sql = "select * from productionstore where ProductionID="+proid;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                storeid = rs.getInt(1);
                sql = "update productionstore set Number=Number+"+number+" where id="+storeid;
                pst = conn.prepareStatement(sql);
                pst.execute();
                (new ProductionStoreOrderControl()).addProductionstoreorder(
                        new Productionstoreorder(
                                -1,proid,number,time,orderid
                        )
                );
            }else{
                JOptionPane.showMessageDialog(null, "此产品无仓库", "提示", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
