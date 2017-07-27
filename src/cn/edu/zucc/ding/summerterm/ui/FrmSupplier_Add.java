package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.SupplierControl;
import cn.edu.zucc.ding.summerterm.model.Supplier;
import cn.edu.zucc.ding.summerterm.util.BaseException;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmSupplier_Add extends JDialog implements ActionListener {
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
    private JButton Add_OK = new JButton("确认");
    private JButton Add_Cancel = new JButton("取消");
    private FrmSupplier t;


    public FrmSupplier_Add(FrmSupplier t) {
        this.t = t;
        this.setLayout(new FlowLayout());
        this.setSize(300, 220);
        this.add(NameL);
        this.add(NameT);
        this.add(AddressL);
        this.add(AddressT);
        this.add(LinkNameL);
        this.add(LinkNameT);
        this.add(LinkPhoneL);
        this.add(LinkPhoneT);
        this.add(IntroductionL);
        this.add(IntroductionT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        Add_OK.addActionListener(this);
        Add_Cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            try{
            if(this.NameT.getText().equals("")||this.AddressT.getText().equals("")||this.LinkNameT.getText().equals("")){
                throw new BaseException("信息填写不完全");
            }if(!DatabaseOP.regex("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",this.LinkPhoneT.getText())) {
                throw new BaseException("联系人电话号码填写有误");
            }}catch (BaseException e1){
                e1.printStackTrace();
                return;
            }
            Supplier s = new Supplier(
                    0,
                    this.NameT.getText(),
                    this.AddressT.getText(),
                    this.LinkNameT.getText(),
                    this.IntroductionT.getText(),
                    this.LinkPhoneT.getText()
            );
            (new SupplierControl()).addSupplier(s);
            this.setVisible(false);
            this.t.reloadTable(null);
            this.removeAll();
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }
}
