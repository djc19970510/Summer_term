package cn.edu.zucc.ding.summerterm.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmProduction extends JPanel implements ActionListener {
    DefaultTableModel tablmod=new DefaultTableModel();
    Object[][] cellData = {{"row1-col1", "row1-col2"},{"row2-col1", "row2-col2"}};
    String[] columnNames = {"col1", "col2"};
    private JTable readerTable = new JTable(cellData, columnNames);

    public  FrmProduction(){
        readerTable.validate();
        readerTable.repaint();
        readerTable.setVisible(true);
        readerTable.setSize(300,300);
        readerTable.setBackground(new Color(111,111,0));
        this.setLayout(new BorderLayout());
        this.add(readerTable,BorderLayout.CENTER);
        this.setVisible(true);
        this.setBackground(new Color(55,55,55));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
