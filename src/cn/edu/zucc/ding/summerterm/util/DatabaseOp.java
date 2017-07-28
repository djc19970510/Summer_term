package cn.edu.zucc.ding.summerterm.util;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;
import cn.edu.zucc.ding.summerterm.model.Customer;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseOP {
    public static Timestamp getdate() // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
    {
        Date dat = null;
        Calendar cd = Calendar.getInstance();
        cd.roll(Calendar.MONTH, -1);
        dat = cd.getTime();
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp date = Timestamp.valueOf(dformat.format(dat));
        return date;
    }

    public static boolean isDouble(String str)
    {
        try
        {
            if(Double.parseDouble(str)>0)
                return true;
            else
                return false;
        }
        catch(NumberFormatException ex){}
        return false;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static String select(String element,String table,String condition){
        String sql = "Select " + element + " from " +table;
        if(!condition.equals("")){
            sql +=" " + condition;
        }
        return sql;
    }
    public static <E extends getID>String update(E e){
        String table = e.getClass().getSimpleName();
        List<String> result = getField(firstUpperCase(table));
        result.remove("ID");
        String sql = "Update "+table+" ";
        sql+= "set ";
        for (int i=0;i<result.size();i++){
            if(i!=0){sql+=",";}
            sql+=result.get(i).toString()+"=?";
        }
        sql+=" ";
        sql+="where ID="+e.getID();
        return sql;
    }

    public static <E extends getID>String delete(E e){
        String table = e.getClass().getSimpleName();
        String sql = "Delete from "+table+" ";
        sql+="where ID="+e.getID();
        return sql;
    }

    public static <E extends  getID>String insert(E e){
        String table = e.getClass().getSimpleName();
        List<String> result = getField(firstUpperCase(table));
        result.remove("ID");
        String sql = "Insert into "+table+"(";
        for (int i=0;i<result.size();i++){
            if(i!=0){sql+=",";}
            sql+=result.get(i).toString();
        }
        sql+=") values(";
        for (int i=0;i<result.size();i++){
            if(i!=0){sql+=",";}
            sql+="?";
        }
        sql+=")";
        return sql;
    }

    public static List<String> getField(String classname){
        List<String> result = new ArrayList<String>();
        try {
            Class clazz = Class.forName("cn.edu.zucc.ding.summerterm.model."+classname);//根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
            Field[] fields = clazz.getDeclaredFields();//根据Class对象获得属性 私有的也可以获得
            for(Field f : fields) {
                System.out.println(f.getName());//打印每个属性的类型名字
                result.add(f.getName());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String firstLowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static Map<String,String> testReflect(Object model) throws Exception{
        Map<String,String> map = new HashMap<>();
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(),(field.get(model)!=null)?field.get(model).toString():"");
        }
        return map;
    }

    public static void main(String[] args){
        Customer c= new Customer();
        System.out.print(select("*","Customer",""));
    }

    public static boolean regex(String regex, String value) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

}
