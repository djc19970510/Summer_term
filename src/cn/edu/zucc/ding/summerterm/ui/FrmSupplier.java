package cn.edu.zucc.ding.summerterm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmSupplier extends JPanel implements ActionListener{
    private JLabel test = new JLabel("123");
    public FrmSupplier(){
        this.setVisible(true);
        this.add(test);
        this.setBackground(new Color(0,255,255));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
