package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.control.ProductionStoreControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.ProductionAndStore;
import cn.edu.zucc.ding.summerterm.model.Productionstore;
import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmStore_addp extends JDialog implements ActionListener {

    private JLabel productionsL= new JLabel("产品");
    private JComboBox probox;
    private JLabel addressL = new JLabel("地址");
    private JTextField addressT= new JTextField(10);
    private JButton Add_OK = new JButton("确定");
    private JButton Add_Cancel = new JButton("取消");
    private FrmStore fs;
    private List<Production> productions = new ArrayList<Production>();
    private String[] productionsString;
    private ProductionAndStore pas;


    public FrmStore_addp(FrmStore fs){
        this.fs = fs;
        this.setSize(new Dimension(180,140));
        productions = (new ProductionControl()).loadAllProduction();
        productionsString = new String[productions.size()];
        for(int i=0;i<productions.size();i++){
            productionsString[i] = productions.get(i).getName();
        }
        this.probox = new JComboBox(productionsString);
        this.probox.setPreferredSize(new Dimension(110,20));
        this.setLayout(new FlowLayout());
        this.add(productionsL);
        this.add(probox);
        this.add(addressL);
        this.add(addressT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setVisible(true);
    }

    public FrmStore_addp(FrmStore fs,ProductionAndStore pas){
        this.pas = pas;
        int j=0;
        this.fs = fs;
        this.setSize(new Dimension(180,140));
        productions = (new ProductionControl()).loadAllProduction();
        productionsString = new String[productions.size()];
        for(int i=0;i<productions.size();i++){
            productionsString[i] = productions.get(i).getName();
            if(productionsString[i].equals(this.pas.getName()))
                j=i;
        }
        this.probox = new JComboBox(productionsString);
        this.probox.setPreferredSize(new Dimension(110,20));
        this.setLayout(new FlowLayout());
        this.add(productionsL);
        this.add(probox);
        this.probox.setSelectedIndex(j);
        this.add(addressL);
        this.add(addressT);
        this.pas.getAddress();
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_OK){
            int sr = probox.getSelectedIndex();
            int id = productions.get(sr).getID();
            try {
                Connection conn = DBUtil.getConnection();
                String sql = "select * from productionstore where ProductionID='"+id+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    Productionstore ms = new Productionstore(
                            this.pas.getID(),this.pas.getNumber(),this.addressT.getText(),id
                    );
                    (new ProductionStoreControl()).addProductionstore(ms);
                }else{
                    Productionstore ms = new Productionstore(
                            0,0,this.addressT.getText(),id
                    );
                    (new ProductionStoreControl()).addProductionstore(ms);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            this.setVisible(false);
            this.removeAll();
            fs.reloadpTable(null);
        }
    }
}
