package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.model.ProductionAndStoreOrder;
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

public class FrmStore_orderp extends JDialog implements ActionListener{
    private Object tblTitle[] = {"序号","产品名", "数量", "时间","订单号","状态"};
    private Object tblData[][];
    DefaultTableModel tablmod = new DefaultTableModel();
    private JTable Producting_infotable = new JTable(tablmod);
    private JScrollPane Producting_infotableheader = new JScrollPane(Producting_infotable);
    private List<ProductionAndStoreOrder> pasos ;
    public FrmStore_orderp(){
        this.setLayout(new BorderLayout());
        this.add(Producting_infotableheader,BorderLayout.CENTER);
        this.setSize(800,600);
        this.setVisible(true);
        this.reloadTable(null);
    }

    protected void reloadTable(String s) {
        try {
            pasos = new ArrayList<ProductionAndStoreOrder>();
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT productionstoreorder.ID,productionstoreorder.ProductionID,production.name,Number,productionstoreorder.Date,productionstoreorder.OrderID " +
                    "FROM mydb.productionstoreorder,production " +
                    "where productionstoreorder.productionid=production.id";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ProductionAndStoreOrder paso = new ProductionAndStoreOrder(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(4),
                        rs.getTimestamp(5),
                        rs.getInt(6),
                        rs.getString(3)
                );
                pasos.add(paso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tblData = new Object[pasos.size()][6];
        for (int i = 0; i < pasos.size(); i++) {
            tblData[i][0] = i+1 + "";
            tblData[i][1] = pasos.get(i).getProductionName();
            tblData[i][2] = pasos.get(i).getNumber()>0?pasos.get(i).getNumber():-pasos.get(i).getNumber();
            tblData[i][3] = pasos.get(i).getDate();
            tblData[i][4] = pasos.get(i).getOrderID();
            tblData[i][5] = pasos.get(i).getNumber()>0?"入库":"出库";
        }
        tablmod.setDataVector(tblData, tblTitle);
        this.Producting_infotable.validate();
        this.Producting_infotable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
