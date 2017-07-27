package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.model.MaterialsAndStoreOrder;
import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmStore_orderm extends JDialog implements ActionListener{
    private Object tblTitle[] = {"序号","材料名", "数量", "时间","订单号","状态"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable Producting_infotable = new JTable(tablmod);
    private JScrollPane Producting_infotableheader = new JScrollPane(Producting_infotable);
    private List<MaterialsAndStoreOrder> masol;
    private JPanel up = new JPanel();
    private JTextField seltext = new JTextField(15);
    private JButton selb = new JButton("查询");
    public FrmStore_orderm(){
        this.setLayout(new BorderLayout());
        this.add(Producting_infotableheader,BorderLayout.CENTER);
        this.add(up,BorderLayout.NORTH);
        up.setLayout(new FlowLayout());
        up.add(seltext);
        up.add(selb);
        this.selb.addActionListener(this);
        this.setSize(800,600);
        this.setVisible(true);
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        try {
            masol = new ArrayList<MaterialsAndStoreOrder>();
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT materialsstoreorder.id,materialsstoreorder.Number,materialsstoreorder.Date,materials.id,materials.name,materialsstoreorder.OrderID " +
                    "FROM materialsstoreorder,materials " +
                    "where MaterialsID=materials.id";
            if(s!=null&&!s.equals(""))
                    sql += " and materials.name like '%"+s+"%'";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                MaterialsAndStoreOrder maso = new MaterialsAndStoreOrder(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getTimestamp(3),
                        rs.getInt(4),
                        rs.getInt(6),
                        rs.getString(5)
                );
                masol.add(maso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tblData = new Object[masol.size()][6];
        for (int i = 0; i < masol.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = masol.get(i).getMaterialsName();
            tblData[i][2] = masol.get(i).getNumber()>0?masol.get(i).getNumber():-masol.get(i).getNumber();
            tblData[i][3] = masol.get(i).getDate();
            tblData[i][4] = masol.get(i).getOrderID();
            tblData[i][5] = masol.get(i).getNumber()>0?"入库:购买材料":"出库:生产";
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Producting_infotable.validate();
        this.Producting_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.selb){
            reloadTable(this.seltext.getText());
        }
    }
}
