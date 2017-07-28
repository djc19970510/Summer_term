package cn.edu.zucc.ding.summerterm;

import cn.edu.zucc.ding.summerterm.util.DBUtil;
import cn.edu.zucc.ding.summerterm.util.DatabaseOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class TestSystem {
    public static void main(String[] args){
        System.out.println(DatabaseOP.getdate());
    }
}
