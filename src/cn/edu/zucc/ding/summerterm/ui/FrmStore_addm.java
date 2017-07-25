package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.MaterialsStoreControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.Materialsstore;
import cn.edu.zucc.ding.summerterm.model.Materialsstoreorder;
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

public class FrmStore_addm extends JDialog implements ActionListener {

    private JLabel materialsL= new JLabel("材料");
    private JComboBox materbox;
    private JLabel addressL = new JLabel("地址");
    private JTextField addressT= new JTextField(10);
    private JButton Add_OK = new JButton("确定");
    private JButton Add_Cancel = new JButton("取消");
    private FrmStore fs;
    private List<Materials> materials = new ArrayList<Materials>();
    private String[] materialsString;

    public FrmStore_addm(FrmStore fs){
        this.fs = fs;
        this.setSize(new Dimension(180,140));
        materials = (new MaterialsControl()).loadAllMaterials();
        materialsString = new String[materials.size()];
        for(int i=0;i<materials.size();i++){
            materialsString[i] = materials.get(i).getName();
        }
        this.materbox = new JComboBox(materialsString);
        this.materbox.setPreferredSize(new Dimension(110,20));
        this.setLayout(new FlowLayout());
        this.add(materialsL);
        this.add(materbox);
        this.add(addressL);
        this.add(addressT);
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
            int sr = materbox.getSelectedIndex();
            int id = materials.get(sr).getID();
            try {
                Connection conn = DBUtil.getConnection();
                String sql = "select * from materialsstore where MaterialsID='"+id+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,  "该材料已经存在仓库存储信息","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Materialsstore ms = new Materialsstore(
                    0,0,this.addressT.getText(),id
            );
            (new MaterialsStoreControl()).addMaterialsstore(ms);
            this.setVisible(false);
            this.removeAll();
            fs.reloadmTable(null);
        }
    }
}
