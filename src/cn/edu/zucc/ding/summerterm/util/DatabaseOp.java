package cn.edu.zucc.ding.summerterm.util;

import cn.edu.zucc.ding.summerterm.Icontrol.getID;
import cn.edu.zucc.ding.summerterm.model.Customer;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseOP {
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
        Customer c = new Customer();
        try {
            System.out.println(insert(c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
