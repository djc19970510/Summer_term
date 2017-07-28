package cn.edu.zucc.ding.summerterm.ui;

import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrmOrder_collm extends JDialog {
    public FrmOrder_collm(String title)
    {
        this.setContentPane(createPanel()); //构造函数中自动创建Java的panel面板

    }

    public static CategoryDataset createDataset() //创建柱状图数据集
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        String sql = "SELECT name,sum(number) FROM mydb.materialsorder,materials where materialsorder.MaterialsID=materials.id and Date>? group by name";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setTimestamp(1,DatabaseOP.getdate());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dataset.setValue(rs.getInt(2),rs.getString(1),rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        JFreeChart chart=ChartFactory.createBarChart("hi", "材料分布",
                "材料订购数量", dataset, PlotOrientation.VERTICAL, false, false, false); //创建一个JFreeChart
        chart.setTitle(new TextTitle("近一月材料订购统计",new Font("宋体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setLabelFont(new Font("宋书",Font.PLAIN,15));
        valueAxis.setTickLabelFont(new Font("宋书",Font.PLAIN,15));
        categoryAxis.setLabelFont(new Font("宋书",Font.PLAIN,15));//设置横坐标字体
        categoryAxis.setTickLabelFont(new Font("宋书",Font.PLAIN,15));
        return chart;
    }

    public JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
    }

}
