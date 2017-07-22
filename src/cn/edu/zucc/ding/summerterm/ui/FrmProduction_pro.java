package cn.edu.zucc.ding.summerterm.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmProduction_pro extends JPanel implements ActionListener{
    private JPanel left = new JPanel();
    private JPanel centor = new JPanel();
    private JPanel right = new JPanel();
    private JButton teset = new JButton("123");

    public FrmProduction_pro(){
        this.setLayout(new BorderLayout());
        left.add(teset);
        this.add(left,BorderLayout.WEST);
        this.add(centor,BorderLayout.CENTER);
        this.add(right,BorderLayout.EAST);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
