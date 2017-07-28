package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.MaterialsControl;
import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.Materials;
import cn.edu.zucc.ding.summerterm.model.MaterialsAndSuppliers;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FrmMaterials_Add extends JDialog implements ActionListener {
    private JLabel NameL =
            new JLabel("　材料名");
    private JLabel PriceL =
            new JLabel("材料价格");
    private JLabel IntroductionL =
            new JLabel("材料描述");
    private JLabel SupplierL =
            new JLabel("　供应商");
    private JTextField NameT = new JTextField(15);
    private JTextField PriceT = new JTextField(15);
    private JTextField IntroductionT = new JTextField(15);
    private JComboBox SupplierT;
    private List<Supplier> suppliers;
    private String[] suppliersString;
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmMaterials fm;
    private MaterialsAndSuppliers mas;

    public FrmMaterials_Add(FrmMaterials fm,MaterialsAndSuppliers mas){
        this.fm = fm;
        this.mas = mas;
        this.setLayout(new FlowLayout());
        this.add(NameL);
        this.add(NameT);
        this.NameT.setText(mas==null?"":mas.getName());
        this.add(PriceL);
        this.add(PriceT);
        this.PriceT.setText(mas==null?"":String.valueOf(mas.getMaterialsBasePrice()));
        this.add(IntroductionL);
        this.add(IntroductionT);
        this.IntroductionT.setText(mas==null?"":mas.getIntroduction());
        this.add(SupplierL);
        this.suppliers = (new SupplierControl()).loadAllSupplier();
        suppliersString = new String[suppliers.size()];
        int j=-1;
        for (int i=0;i<suppliers.size();i++){
            suppliersString[i] = suppliers.get(i).getName();
            if(mas!=null){
                if(suppliersString[i].equals(mas.getSupplierName())){
                    j=i;
                }
            }
        }
        SupplierT = new JComboBox(suppliersString);
        if(j!=-1)
            SupplierT.setSelectedIndex(j);
        this.add(SupplierT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        this.Add_OK.addActionListener(this);
        this.Add_Cancel.addActionListener(this);
        this.setSize(new Dimension(300,170));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }else if(e.getSource()==this.Add_OK){
            try {
                if(this.suppliers.size()<=0){
                    throw new BaseException("没有供应商信息，请先输入供应商");
                }if(this.NameT.getText().equals("")){
                    throw new BaseException("没有输入材料名称");
                }if(this.IntroductionT.getText().equals("")){
                    throw new BaseException("没有输入材料介绍");
                }if(!DatabaseOP.isDouble(this.PriceT.getText())){
                    throw new BaseException("价格输入不合法");
                }
            }catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            if(mas==null){
                try{
                    Connection conn = DBUtil.getConnection();
                    String sql = "select * from materials where Name=?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1,NameT.getText());
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        rs.close();
                        pst.close();
                        conn.close();
                        throw new BaseException("材料名称有重复");
                    }
                    rs.close();
                    pst.close();
                    conn.close();
                }catch (SQLException e1){
                    e1.printStackTrace();
                } catch (BaseException e1){
                    e1.printStackTrace();
                    return;
                }
            }
            int scom = this.SupplierT.getSelectedIndex();
            Supplier s = this.suppliers.get(scom);
            Materials ms = new Materials(
               mas==null?-1:mas.getID(),NameT.getText(),Double.valueOf(PriceT.getText()),IntroductionT.getText(),s.getID()
            );
            if(this.mas==null)
                (new MaterialsControl()).addMaterials(ms);
            else
                (new MaterialsControl()).modifyMaterials(ms);
            fm.reloadTable(null,null,null);
            this.setVisible(false);
            this.removeAll();
        }
    }
}
