package cn.edu.zucc.ding.summerterm;

import cn.edu.zucc.ding.summerterm.util.DBUtil;

import javax.xml.stream.events.EndElement;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;

public class EntityMysql {


    private String packageOutPath = "cn.edu.zucc.ding.summerterm.model";
    private static String[] tablenames = {"customer","linkman", "materials", "materialsorder", "materialsstore", "materialsstoreorder", "productingorder", "production", "productiondetails", "productionorder", "productionstore", "productionstoreorder", "productiontype", "supplier", "supplier_has_materials"};
    private String[] colnames;
    private String[] colTypes;
    private boolean util = false;
    private boolean sql = false;

    public EntityMysql(){}
    public EntityMysql(String tablename) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBUtil.getConnection();
            PreparedStatement pStemt = con.prepareStatement("select * from " + tablename);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();
            colnames = new String[size];
            colTypes = new String[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    sql = true;
                }
            }
            String content = parse(colnames, colTypes,tablename);
            File directory = new File("");
            String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/") + "/"
                    + initcap(tablename) + ".java";
            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(content);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String parse(String[] colnames, String[] colTypes,String tablename) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";");
        if (util) {
            sb.append("import java.util.Date;");
        }
        if (sql) {
            sb.append("import java.sql.*;");
        }
        sb.append("public class " + initcap(tablename) + "{");
        for (int i = 0; i < colnames.length; i++) {
            sb.append("private " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";");
        }
        sb.append("public " + initcap(tablename) + "(){}");
        sb.append("public " + initcap(tablename) + "(");
        for (int j = 0; j < colnames.length; j++) {
            sb.append(sqlType2JavaType(colTypes[j]) + " " + colnames[j]);
            if (j != colnames.length - 1)
                sb.append(",");
        }
        sb.append("){this();");
        for (int s = 0; s < colnames.length; s++) {
            sb.append("this." + colnames[s] + "=" + colnames[s] + ";");
        }
        sb.append("}");
        for (int i = 0; i < colnames.length; i++) {
            sb.append("public void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + colnames[i]
                    + "){");
            sb.append("this." + colnames[i] + "=" + colnames[i] + ";");
            sb.append("}");
            sb.append("public " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){");
            sb.append("return " + colnames[i] + ";");
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }


    private String initcap(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }
        System.out.println(sqlType);
        return null;
    }


    public static void main(String[] args) {
        for (int i=0;i<tablenames.length;i++)
            new cn.edu.zucc.ding.summerterm.EntityMysql(tablenames[i]);
    }
}