package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.OtherControl;
import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.control.ProductionDetailControl;
import cn.edu.zucc.ding.summerterm.control.ProductionTypeControl;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndDetails;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiondetails;
import cn.edu.zucc.ding.summerterm.model.Productiontype;
import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FrmProduction extends JPanel implements ActionListener {
    private JPanel maintask = new JPanel();

    private JPanel main_left_task = new JPanel();
        private JPanel main_left_top_task = new JPanel();
        private JPanel main_left_center_task = new JPanel();
        private JButton add_Type = new JButton("增加类型");
        private JButton del_Type = new JButton("删除类型");
        private JButton mod_Type = new JButton("修改类型");
        private JButton sel_Type = new JButton("查询类型");
        private JTextField sel_Type_Text = new JTextField(15);
        private Object typeTitle[] = {"产品类型序号","产品类型名", "产品类型描述"};
        private Object typeData[][];
        DefaultTableModel typetablmod = new DefaultTableModel();
        protected List<Productiontype> productiontypes;
        protected JTable table_productionType =  new JTable(typetablmod);
        private JScrollPane table_scrollproductionType = new JScrollPane(table_productionType);

    private JPanel main_center_task = new JPanel();
        private JPanel main_center_top_task = new JPanel();
            private JButton add_pro = new JButton("增加产品");
            private JButton del_pro = new JButton("删除产品");
            private JButton mod_pro = new JButton("修改产品");
        private JPanel main_center_center_task = new JPanel();
            private Object proTitle[] = {"产品序号","产品名称", "产品价格"};
            private Object proData[][];
            DefaultTableModel protablmod = new DefaultTableModel();
            protected List<Production> productions;
            protected JTable table_production =  new JTable(protablmod);
            private JScrollPane table_scrollproduction = new JScrollPane(table_production);



    private JPanel main_right_task = new JPanel();
        private JPanel main_right_center_task = new JPanel();
            private Object detailTitle[] = {"材料名称","材料数量"};
            private Object detailData[][];
            DefaultTableModel detailtablmod = new DefaultTableModel();
            protected List<MaterialsAndDetails> productiondetails;
            private JTable table_productiondetails =  new JTable(detailtablmod);
            private JScrollPane table_scrollproductiondetails = new JScrollPane(table_productiondetails);
        private JPanel main_right_buttom_task = new JPanel();
            private JButton add_detail = new JButton("增加产品细节");
            private JButton del_detail = new JButton("删除产品细节");
            private JButton mod_detail = new JButton("修改产品细节");





    public  FrmProduction(){
        this.setLayout(new BorderLayout());
        this.add(maintask,BorderLayout.CENTER);
        this.setVisible(true);


        maintask.setVisible(true);
        maintask.setLayout(new BorderLayout());

        maintask.add(main_left_task,BorderLayout.WEST);
        maintask.add(main_center_task,BorderLayout.CENTER);
        maintask.add(main_right_task,BorderLayout.EAST);
        //左侧任务栏
        main_left_task.setVisible(true);
        main_left_task.setPreferredSize(new Dimension(350,200));
        main_left_task.setLayout(new BorderLayout());
        main_left_task.add(main_left_top_task,BorderLayout.NORTH);
        main_left_task.add(main_left_center_task,BorderLayout.CENTER);
            //左上任务栏
            main_left_top_task.setLayout(new BorderLayout());
            main_left_top_task.add(table_scrollproductionType,BorderLayout.CENTER);
            main_left_top_task.setPreferredSize(new Dimension(300,250));
            table_productionType.addMouseListener(new myMouseAdapterType(this));
            //左中任务栏
            main_left_center_task.add(add_Type);
            main_left_center_task.add(del_Type);
            main_left_center_task.add(mod_Type);
            main_left_center_task.add(sel_Type_Text);
            main_left_center_task.add(sel_Type);
            this.add_Type.addActionListener(this);
            this.mod_Type.addActionListener(this);
            this.del_Type.addActionListener(this);
            this.sel_Type.addActionListener(this);
            main_left_center_task.setPreferredSize(new Dimension(300,100));



        //中部任务栏
        main_center_task.setLayout(new BorderLayout());
        main_center_task.add(main_center_top_task,BorderLayout.NORTH);
        main_center_task.add(main_center_center_task,BorderLayout.CENTER);
            //中部顶部任务栏
            main_center_top_task.setLayout(new FlowLayout());
            main_center_top_task.add(add_pro);
            main_center_top_task.add(del_pro);
            main_center_top_task.add(mod_pro);
            this.add_pro.addActionListener(this);
            this.mod_pro.addActionListener(this);
            this.del_pro.addActionListener(this);
            //中部中部任务栏
            main_center_center_task.setLayout(new BorderLayout());
            main_center_center_task.add(table_scrollproduction,BorderLayout.CENTER);
            table_production.addMouseListener(new myMouseAdapterPro(this));

        //右侧任务栏
        main_right_task.setVisible(true);
//        main_right_task.setPreferredSize(new Dimension(400,1));
        main_right_task.setLayout(new BorderLayout());
            //右侧中部任务栏
            main_right_task.add(main_right_center_task,BorderLayout.CENTER);
            main_right_center_task.add(table_scrollproductiondetails,BorderLayout.CENTER);
            //右侧底部任务栏
            main_right_task.add(main_right_buttom_task,BorderLayout.SOUTH);
            main_right_buttom_task.add(add_detail);
            main_right_buttom_task.add(del_detail);
            main_right_buttom_task.add(mod_detail);
        this.add_detail.addActionListener(this);
        this.del_detail.addActionListener(this);
        this.mod_detail.addActionListener(this);



        this.reloadAllType("");
        this.reloadAllDetails(new Production(-1,"",1,1));
        this.reloadAll(new Productiontype(-1,"",""));
    }

    public void reloadAllType(String string) {
        if(string.equals("")){
            productiontypes = (new ProductionTypeControl()).loadAllProductiontype();
        }else{
            productiontypes = (new ProductionTypeControl()).loadSomeProductiontype(string);
        }
        typeData = new Object[productiontypes.size()][3];
        for (int i = 0; i < productiontypes.size(); i++) {
            typeData[i][0] = i+1 + "";
            typeData[i][1] = productiontypes.get(i).getName();
            typeData[i][2] = productiontypes.get(i).getIntroduction();
        }
        typetablmod.setDataVector(typeData, typeTitle);
        this.table_productionType.validate();
        this.table_productionType.repaint();
        return;
    }

    public void reloadAll(Productiontype pt) {
        productions = (new ProductionControl()).loadSomeProduction(pt);
        proData = new Object[productions.size()][3];
        for (int i = 0; i < productions.size(); i++) {
            proData[i][0] = i+1 + "";
            proData[i][1] = productions.get(i).getName();
            proData[i][2] = productions.get(i).getPrice()+"";
        }
        protablmod.setDataVector(proData, proTitle);
        this.table_production.validate();
        this.table_production.repaint();
        return;
    }


    public void reloadAllDetails(Production pt){
        productiondetails = (new OtherControl()).loadSomeMaterialsAndDetails(pt);
        detailData = new Object[productiondetails.size()][2];
        for (int i = 0; i < productiondetails.size(); i++) {
            detailData[i][0] = productiondetails.get(i).getName()+"";
            detailData[i][1] = productiondetails.get(i).getNumber()+"";
        }
        detailtablmod.setDataVector(detailData, detailTitle);
        this.table_production.validate();
        this.table_production.repaint();
        return;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.add_Type){
            FrmProduction_Type dlg = new FrmProduction_Type(this,null);
            dlg.setVisible(true);
        } else if (e.getSource()==this.mod_Type) {
            int i=this.table_productionType.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择产品类型","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmProduction_Type dlg = new FrmProduction_Type(this,productiontypes.get(i));
            dlg.setVisible(true);
        } else if(e.getSource()==this.del_Type){
            int i=this.table_productionType.getSelectedRow();
            if(i<0){
                JOptionPane.showMessageDialog(null,  "请选择产品类型","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Productiontype pt = productiontypes.get(i);
            try {
                String sql = "select * from production where ProductionTypeID=?";
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,pt.getID());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,  "此类型存在产品，无法删除","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    ProductionTypeControl ptc = new ProductionTypeControl();
                    ptc.delProductiontype(pt);
                    this.reloadAllType("");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource()==this.sel_Type){
            String string = this.sel_Type_Text.getText();
            this.reloadAllType(string);
        }else if(e.getSource()==this.add_pro){
            FrmProduction_pro dlg = new FrmProduction_pro(this,null);
        }else if(e.getSource()==this.mod_pro){
            int sr = table_production.getSelectedRow();
            Production pro = productions.get(sr);
            FrmProduction_pro dlg = new FrmProduction_pro(this,pro);
        }else if(e.getSource()==this.del_pro){
            int sr = table_production.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请选择要删除的产品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            Production pro = productions.get(sr);
            int res = JOptionPane.showConfirmDialog(null, "确认删除该产品信息（包括该产品细节）", "提示", JOptionPane.YES_NO_OPTION);
            if(res == JOptionPane.YES_OPTION){
                String sql = "delete from productiondetails where productionid=?";
                try {
                    Connection conn = DBUtil.getConnection();
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1,pro.getID());
                    pst.execute();
                    sql = "delete from production where id=?";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1,pro.getID());
                    pst.execute();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                int sr2 = table_productionType.getSelectedRow();
                this.reloadAll(productiontypes.get(sr2));
                this.reloadAllDetails(new Production(-1,"",0,0));
            }
        }else if(e.getSource()==this.add_detail){
            if(this.table_production.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null,  "请先选择产品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            FrmProduction_details dlg = new FrmProduction_details(this,null);
        }else if(e.getSource()==this.mod_detail){
            if(this.table_production.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null,  "请先选择产品","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int sr = this.table_productiondetails.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请先选择产品详细信息","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            MaterialsAndDetails prod = productiondetails.get(sr);
            FrmProduction_details dlg = new FrmProduction_details(this,prod);
        }else if(e.getSource()==this.del_detail){
            int sr = this.table_productiondetails.getSelectedRow();
            if(sr<0){
                JOptionPane.showMessageDialog(null,  "请先选择产品详细信息","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            MaterialsAndDetails prod = productiondetails.get(sr);
            String sql = "delete from productiondetails where productionid=? and materialsid=?";
            try {
                Connection conn = DBUtil.getConnection();
                PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,prod.getProductionID());
                pst.setInt(2,prod.getMaterialsID());
                pst.execute();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            int srp = table_production.getSelectedRow();
            this.reloadAllDetails(productions.get(srp));
        }
    }

    private class myMouseAdapterType extends MouseAdapter{
        FrmProduction p;
        public myMouseAdapterType(FrmProduction p){
            this.p = p;
        }
        public void mouseClicked(MouseEvent e) {
            int sr;
            if ((sr = p.table_productionType.getSelectedRow()) == -1) {
                return;
            }else{
                sr = p.table_productionType.getSelectedRow();
                Productiontype pt = p.productiontypes.get(sr);
                p.reloadAll(pt);
                p.reloadAllDetails(new Production(-1,"",0,0));
            }

        }
    }

    private class myMouseAdapterPro extends MouseAdapter{
        FrmProduction p;
        public myMouseAdapterPro(FrmProduction p){
            this.p = p;
        }
        public void mouseClicked(MouseEvent e) {
            int sr;
            if ((sr = p.table_production.getSelectedRow()) == -1) {
                return;
            }else{
                sr = p.table_production.getSelectedRow();
                Production pt = p.productions.get(sr);
                p.reloadAllDetails(pt);
            }
        }
    }

}
