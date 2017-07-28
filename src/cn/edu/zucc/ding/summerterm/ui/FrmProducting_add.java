package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.*;
import cn.edu.zucc.ding.summerterm.model.*;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
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

    public FrmProducting_add(FrmProducting fp) {
        this.fp = fp;
        this.setSize(new Dimension(180, 200));
        this.setVisible(true);
        productions = (new ProductionControl()).loadAllProduction();
        productionsString = new String[productions.size()];
        for (int i = 0; i < productions.size(); i++) {
            productionsString[i] = productions.get(i).getName();
        }
        this.productionbox = new JComboBox(productionsString);
        this.productionbox.setPreferredSize(new Dimension(110, 20));
        this.setLayout(new FlowLayout());
        this.add(productionL);
        this.add(productionbox);
        this.add(numberL);
        this.add(numberT);
        timeT.setText(new Timestamp(System.currentTimeMillis()).toString());
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
        if (e.getSource() == this.Add_OK) {
            int sr = productionbox.getSelectedIndex();
            int id = productions.get(sr).getID();
            try {
                Connection conn = DBUtil.getConnection();
                String sql = "select * from productionstore where ProductionID=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,id);
                ResultSet rs= pst.executeQuery();
                if(!rs.next()){
                    throw new BaseException("该产品未设置存放仓库");
                }
                if(!DatabaseOP.isDouble(this.numberT.getText())){
                    throw new BaseException("产品数量不合法");
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            double number = Double.valueOf(this.numberT.getText());
            if (examMaterial(id, number)) {
                System.out.println("in");
                Productingorder po = new Productingorder(
                        -1, id, number, Timestamp.valueOf(this.timeT.getText())
                );
                (new ProductingOrderControl()).addProductingorder(po);
                Connection conn=null;
                try {
                    conn = DBUtil.getConnection();
                    String sql = "select id from productingorder order by id DESC ";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    int orderid;
                    if (rs.next()) {
                        orderid = rs.getInt(1);
                        Productionin(id, number, orderid, Timestamp.valueOf(this.timeT.getText()));
                        Materialsout(id, number, orderid, Timestamp.valueOf(this.timeT.getText()));
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
            }
            this.setVisible(false);
            this.removeAll();
            fp.reloadTable(null);
        } else if (e.getSource() == this.Add_Cancel) {
            this.setVisible(false);
            this.removeAll();
        }
    }

    private boolean examMaterial(int id, double number) {
        String sql = "SELECT MaterialsNumber,Number FROM mydb.productiondetails,materialsstore where productiondetails.MaterialsID=materialsstore.MaterialsID and Productionid=" + id;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                try {
                    throw new BaseException("材料仓库不存在或产品细节出错");
                } catch (BaseException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            do{
                if (rs.getDouble(1) * number > rs.getDouble(2)) {
                    JOptionPane.showMessageDialog(null, "库存不足", "提示", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }while (rs.next());
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void Materialsout(int proid, double number, int orderid, Timestamp time) {
        try {
            List<Productiondetails> result = new ArrayList<Productiondetails>();
            Connection conn = DBUtil.getConnection();
            String sql = "select * from productiondetails where ProductionID=" + proid;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result.add(
                        new Productiondetails(
                                rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4)
                        ));
            }
            for (int i = 0; i < result.size(); i++) {
                (new MaterialsStoreOrderControl()).addMaterialsstoreorder(new Materialsstoreorder(-1, result.get(i).getMaterialsNumber() * number * -1,
                        time, result.get(i).getMaterialsID(), orderid));
                sql = "update materialsstore set Number=Number-? where MaterialsID=?";
                pst = conn.prepareStatement(sql);
                pst.setDouble(1, result.get(i).getMaterialsNumber() * number);
                pst.setInt(2, result.get(i).getMaterialsID());
                pst.execute();
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void Productionin(int proid, double number, int orderid, Timestamp time) throws BaseException {
        try {
            int storeid;
            Connection conn = DBUtil.getConnection();
            String sql = "select * from productionstore where ProductionID=" + proid;
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                storeid = rs.getInt(1);
                sql = "update productionstore set Number=Number+" + number + " where id=" + storeid;
                pst = conn.prepareStatement(sql);
                pst.execute();
                (new ProductionStoreOrderControl()).addProductionstoreorder(
                        new Productionstoreorder(
                                -1, proid, number, time, orderid
                        )
                );
            } else {
                throw new BaseException("此产品无存放地点");
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
