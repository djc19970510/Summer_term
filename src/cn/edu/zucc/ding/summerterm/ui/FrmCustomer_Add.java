package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.control.CustomerControl;
import cn.edu.zucc.ding.summerterm.model.Customer;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmCustomer_Add extends JDialog implements ActionListener {
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
    private FrmCustomer t;

    public FrmCustomer_Add(FrmCustomer t) {
        this.t = t;
        this.setLayout(new FlowLayout());
        this.setSize(300, 190);
        this.add(NameL);
        this.add(NameT);
        this.add(AddressL);
        this.add(AddressT);
        this.add(LinkNameL);
        this.add(LinkNameT);
        this.add(LinkPhoneL);
        this.add(LinkPhoneT);
        this.add(Add_OK);
        this.add(Add_Cancel);
        Add_OK.addActionListener(this);
        Add_Cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.Add_OK){
            if(this.NameT.getText().equals("")||this.AddressT.getText().equals("")||this.LinkNameT.getText().equals("")){
                JOptionPane.showMessageDialog(null,  "信息填写不完全","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }else if(!DatabaseOP.regex("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",this.LinkPhoneT.getText())) {
                JOptionPane.showMessageDialog(null,  "联系人电话号码填写有误","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }Customer c = new Customer(
                    0,
                    this.NameT.getText(),
                    this.AddressT.getText(),
                    this.LinkNameT.getText(),
                    this.LinkPhoneT.getText()
            );
            (new CustomerControl()).addCustomer(c);
            this.setVisible(false);
            this.t.reloadTable(null);
            this.removeAll();
        }else if(e.getSource()==this.Add_Cancel){
            this.setVisible(false);
            this.removeAll();
        }
    }
}
