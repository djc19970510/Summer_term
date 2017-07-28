package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.ProductionTypeControl;
import cn.edu.zucc.ding.summerterm.model.Productiontype;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrmProduction_Type extends JDialog implements ActionListener{
    private JLabel NameL =
            new JLabel("　产品类型名");
    private JLabel IntroductionL =
            new JLabel("产品类型描述");
    private JTextField NameT = new JTextField(15);
    private JTextField IntroductionT = new JTextField(15);
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmProduction f;
    private Productiontype pt;

    public FrmProduction_Type(FrmProduction f, Productiontype p){
        this.f = f;
        this.pt = p;

        this.setSize(new Dimension(300,135));
        this.setLayout(new FlowLayout());
        this.NameT.setText(p!=null?p.getName():"");
        this.IntroductionT.setText(p!=null?p.getIntroduction():"");
        this.add(NameL);
        this.add(NameT);
        this.add(IntroductionL);
        this.add(IntroductionT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            try {
                if(this.NameT.getText().equals("")){
                    throw new BaseException("未输入产品类别名称");
                }else if(this.IntroductionT.getText().equals("")){
                    throw new BaseException("未输入产品类别描述");
                }
                if(pt==null){
                    Connection conn = DBUtil.getConnection();
                    String sql = "select * from productiontype where Name=?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1,this.NameT.getText());
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        rs.close();
                        pst.close();
                        conn.close();
                        throw new BaseException("产品类型名重复");
                    }
                    rs.close();
                    pst.close();
                    conn.close();
                }
            }catch (SQLException e1) {
                e1.printStackTrace();
            }catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            if (pt==null) {
                Productiontype p = new Productiontype(1,this.NameT.getText(),this.IntroductionT.getText());
                (new ProductionTypeControl()).addProductiontype(p);
            }
            else {
                pt.setName(this.NameT.getText());
                pt.setIntroduction(this.IntroductionT.getText());
                (new ProductionTypeControl()).modifyProductiontype(pt);
            }
            this.f.reloadAllType("");
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }


}
