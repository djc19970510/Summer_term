package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.*;
import cn.edu.zucc.ding.summerterm.model.Customer;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productionorder;
import cn.edu.zucc.ding.summerterm.model.Productionstoreorder;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class FrmOrder_soldmaterials extends JDialog implements ActionListener{
    private JLabel materials = new JLabel("产品");
    private JComboBox productionsbox;
    private JLabel numberL = new JLabel("数量");
    private JTextField numberT = new JTextField(10);
    private JLabel priceL = new JLabel("价格");
    private JTextField priceT = new JTextField(10);
    private JLabel DateL = new JLabel("时间");
    private JTextField DateT = new JTextField(10);
    private JLabel customerL = new JLabel("客户");
    private JComboBox customersbox;
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmOrder fo;
    private List<Production> productions;
    private List<Customer> customers;

    public FrmOrder_soldmaterials(FrmOrder fo){
        productions = (new ProductionControl()).loadAllProduction();
        customers = (new CustomerControl()).loadAllCustomer();
        String[] productionistring = new String[productions.size()];
        for(int i=0;i<productions.size();i++){
            productionistring[i] = productions.get(i).getName();
        }
        String[] customerstring = new String[customers.size()];
        for (int i=0;i<customers.size();i++){
            customerstring[i] = customers.get(i).getName();
        }
        productionsbox = new JComboBox(productionistring);
        productionsbox.setPreferredSize(new Dimension(110,20));
        customersbox = new JComboBox(customerstring);
        customersbox.setPreferredSize(new Dimension(110,20));
        this.fo = fo;
        this.setSize(new Dimension(180,200));
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.add(materials);
        this.add(productionsbox);
        this.add(numberL);
        this.add(numberT);
        this.add(priceL);
        this.add(priceT);
        this.add(DateL);
        this.add(DateT);
        DateT.setText(new Timestamp(System.currentTimeMillis()).toString());
        this.add(customerL);
        this.add(customersbox);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_Cancel.addActionListener(this);
        this.Add_OK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            int id=-1;
            int sr = productionsbox.getSelectedIndex();
            int src = customersbox.getSelectedIndex();
            Production p = productions.get(sr);
            Customer c = customers.get(src);
            try {
                if(!DatabaseOP.isDouble(this.numberT.getText())||!DatabaseOP.isDouble(this.priceT.getText())){
                    throw new BaseException("数量或价格输入不合法");
                }
                String sql = "select number from productionstore where ProductionID="+p.getID();
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    if(Double.valueOf(numberT.getText())>rs.getInt(1)) {
                        JOptionPane.showMessageDialog(null, "库存不足", "提示", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "不存在此产品", "提示", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }catch (BaseException e1){
                e1.printStackTrace();
            }


            Productionorder po = new Productionorder(-1,Double.valueOf(priceT.getText()),Double.valueOf(numberT.getText()),
                    Timestamp.valueOf(DateT.getText()),c.getID(),p.getID());
            (new ProductionOrderControl()).addProductionorder(po);
            String sql = "select id from productionorder order by id desc";
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    id=rs.getInt(1);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            Productionstoreorder pso = new Productionstoreorder(-1,p.getID(),-Double.valueOf(numberT.getText()),Timestamp.valueOf(DateT.getText()),id);
            (new ProductionStoreOrderControl()).addProductionstoreorder(pso);
            sql = "update productionstore set Number=Number-? where ProductionID=?";
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setDouble(1,Double.valueOf(numberT.getText()));
                pst.setInt(2,p.getID());
                System.out.println(p.getID());
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
}}
