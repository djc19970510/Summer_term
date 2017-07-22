package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmSupplier_Mod extends JDialog implements ActionListener {
    private JLabel NameL =
            new JLabel("　　　姓名");
    private JLabel AddressL =
            new JLabel("　　　地址");
    private JLabel LinkNameL =
            new JLabel("联系人姓名");
    private JLabel IntroductionL =
            new JLabel("　　　描述");
    private JLabel LinkPhoneL =
            new JLabel("联系人电话");
    private JTextField NameT = new JTextField(15);
    private JTextField AddressT = new JTextField(15);
    private JTextField LinkNameT = new JTextField(15);
    private JTextField IntroductionT = new JTextField(15);
    private JTextField LinkPhoneT = new JTextField(15);
    private JButton Mod_OK = new JButton("确认");
    private JButton Mod_Cancel = new JButton("取消");
    private FrmSupplier t;
    private Supplier s;
    public FrmSupplier_Mod(FrmSupplier t, Supplier s){
        this.t = t;
        this.s = s;
        this.setLayout(new FlowLayout());
        this.setSize(300, 220);
        this.add(NameL);
        this.add(NameT);
        this.NameT.setText(s.getName());
        this.add(AddressL);
        this.add(AddressT);
        this.AddressT.setText(s.getAddress());
        this.add(LinkNameL);
        this.add(LinkNameT);
        this.LinkNameT.setText(s.getLinkName());
        this.add(LinkPhoneL);
        this.add(LinkPhoneT);
        this.LinkPhoneT.setText(s.getLinkPhone());
        this.add(IntroductionL);
        this.add(IntroductionT);
        this.IntroductionT.setText(s.getIntroduction());
        this.add(Mod_OK);
        this.add(Mod_Cancel);
        Mod_OK.addActionListener(this);
        Mod_Cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Mod_OK){
            if(this.NameT.getText().equals("")||this.AddressT.getText().equals("")||this.LinkNameT.getText().equals("")){
                JOptionPane.showMessageDialog(null,  "信息填写不完全","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }else if(!  DatabaseOP.regex("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",this.LinkPhoneT.getText())) {
                JOptionPane.showMessageDialog(null,  "联系人电话号码填写有误","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.s.setName(this.NameT.getText());
            this.s.setAddress(this.AddressT.getText());
            this.s.setLinkName(this.LinkNameT.getText());
            this.s.setIntroduction(this.IntroductionT.getText());
            this.s.setLinkPhone(this.LinkPhoneT.getText());
            (new SupplierControl()).modifySupplier(s);
            this.setVisible(false);
            this.t.reloadTable();
            System.out.println("OK");
            this.removeAll();
        }else if(e.getSource()==this.Mod_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }
}
