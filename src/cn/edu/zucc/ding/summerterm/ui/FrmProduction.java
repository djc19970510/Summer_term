package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.ProductionControl;
import cn.edu.zucc.ding.summerterm.control.ProductionDetailControl;
import cn.edu.zucc.ding.summerterm.control.ProductionTypeControl;
import cn.edu.zucc.ding.summerterm.model.Production;
import cn.edu.zucc.ding.summerterm.model.Productiondetails;
import cn.edu.zucc.ding.summerterm.model.Productiontype;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        private Object typeTitle[] = {"产品类型ID","产品类型名", "产品类型描述"};
        private Object typeData[][];
        DefaultTableModel typetablmod = new DefaultTableModel();
        List<Productiontype> productiontypes;
        private JTable table_productionType =  new JTable(typetablmod);
        private JScrollPane table_scrollproductionType = new JScrollPane(table_productionType);

    private JPanel main_center_task = new JPanel();
        private JPanel main_center_top_task = new JPanel();
            private JButton add_pro = new JButton("增加产品");
            private JButton del_pro = new JButton("删除产品");
            private JButton mod_pro = new JButton("修改产品");
            private JTextField sel_pro_text = new JTextField(20);
            private JButton sel_pro = new JButton("查询产品");
        private JPanel main_center_center_task = new JPanel();
            private Object proTitle[] = {"产品ID","产品名称", "产品价格"};
            private Object proData[][];
            DefaultTableModel protablmod = new DefaultTableModel();
            List<Production> productions;
            private JTable table_production =  new JTable(protablmod);
            private JScrollPane table_scrollproduction = new JScrollPane(table_production);



    private JPanel main_right_task = new JPanel();
        private JPanel main_right_center_task = new JPanel();
            private Object detailTitle[] = {"材料名称","材料数量"};
            private Object detailData[][];
            DefaultTableModel detailtablmod = new DefaultTableModel();
            List<Productiondetails> productiondetails;
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

        maintask.setBackground(new Color(5,5,5));
        maintask.setVisible(true);
        maintask.setLayout(new BorderLayout());

        maintask.add(main_left_task,BorderLayout.WEST);
        maintask.add(main_center_task,BorderLayout.CENTER);
        maintask.add(main_right_task,BorderLayout.EAST);
        //左侧任务栏
        main_left_task.setVisible(true);
        main_left_task.setPreferredSize(new Dimension(350,200));
        main_left_task.setBackground(new Color(222,222,22));
        main_left_task.setLayout(new BorderLayout());
        main_left_task.add(main_left_top_task,BorderLayout.NORTH);
        main_left_task.add(main_left_center_task,BorderLayout.CENTER);
            //左上任务栏
            main_left_top_task.setLayout(new BorderLayout());
            main_left_top_task.add(table_scrollproductionType,BorderLayout.CENTER);
            main_left_top_task.setPreferredSize(new Dimension(300,250));
            //左中任务栏
            main_left_center_task.add(add_Type);
            main_left_center_task.add(del_Type);
            main_left_center_task.add(mod_Type);
            main_left_center_task.add(sel_Type_Text);
            main_left_center_task.add(sel_Type);
            main_left_center_task.setBackground(new Color(44,44,233));
            main_left_center_task.setPreferredSize(new Dimension(300,100));

        //中部任务栏
        main_center_task.setBackground(new Color(222,77,22));
        main_center_task.setLayout(new BorderLayout());
        main_center_task.add(main_center_top_task,BorderLayout.NORTH);
        main_center_task.add(main_center_center_task,BorderLayout.CENTER);
            //中部顶部任务栏
            main_center_top_task.setLayout(new FlowLayout());
            main_center_top_task.add(add_pro);
            main_center_top_task.add(del_pro);
            main_center_top_task.add(mod_pro);
            main_center_top_task.add(sel_pro_text);
            main_center_top_task.add(sel_pro);
            //中部中部任务栏
            main_center_center_task.setBackground(new Color(122,33,240));
            main_center_center_task.setLayout(new BorderLayout());
            main_center_center_task.add(table_scrollproduction,BorderLayout.CENTER);

        //右侧任务栏
        main_right_task.setVisible(true);
        main_right_task.setBackground(new Color(222,0,0));
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



        this.reloadAll();
        this.reloadAllType();
        this.reloadAllDetails();
    }

    public void reloadAllType() {
        productiontypes = (new ProductionTypeControl()).loadAllProductiontype();
        typeData = new Object[productiontypes.size()][3];
        for (int i = 0; i < productiontypes.size(); i++) {
            typeData[i][0] = productiontypes.get(i).getID() + "";
            typeData[i][1] = productiontypes.get(i).getName();
            typeData[i][2] = productiontypes.get(i).getIntroduction();
        }
        typetablmod.setDataVector(typeData, typeTitle);
        this.table_productionType.validate();
        this.table_productionType.repaint();
        return;
    }

    public void reloadAll() {
        productions = (new ProductionControl()).loadAllProduction();
        proData = new Object[productions.size()][3];
        for (int i = 0; i < productions.size(); i++) {
            proData[i][0] = productions.get(i).getID() + "";
            proData[i][1] = productions.get(i).getName();
            proData[i][2] = productions.get(i).getPrice()+"";
        }
        protablmod.setDataVector(proData, proTitle);
        this.table_production.validate();
        this.table_production.repaint();
        return;
    }


    public void reloadAllDetails(){
        productiondetails = (new ProductionDetailControl()).loadAllProductiondetails();
        detailData = new Object[productiondetails.size()][2];
        for (int i = 0; i < productiondetails.size(); i++) {
            detailData[i][0] = productiondetails.get(i).getMaterialsID()+"";
            detailData[i][1] = productiondetails.get(i).getMaterialsNumber()+"";
        }
        detailtablmod.setDataVector(detailData, detailTitle);
        this.table_production.validate();
        this.table_production.repaint();
        return;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
