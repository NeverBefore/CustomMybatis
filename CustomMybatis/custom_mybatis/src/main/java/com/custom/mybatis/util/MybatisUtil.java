package com.custom.mybatis.util;

import javax.xml.ws.soap.MTOM;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 完成result跟目标类的转换
 *
 * @author Admin
 */
public class MybatisUtil {

    /**
     * list集合转换
     *
     * @param set
     * @param className
     * @param <T>
     * @return
     */
    public static <T> List<T> getListByResult(ResultSet set, String className) {

        // 1.创建方法的返回值
        List<T> list = new ArrayList<T>();
        // 获取表的列名
        try {
            ResultSetMetaData md = set.getMetaData();
            // 获取列的个数
            int count = md.getColumnCount();
            // 列名集合
            List<String> cnames = new ArrayList<String>();
            for (int i = 1; i <= count; i++) {
                cnames.add(md.getColumnName(i));
            }
            // 加载类结构
            Class clz = Class.forName(className);
            // 获取所有的方法
            Method[] methods = clz.getMethods();
            // 遍历resultSet
            T object = null;
            while (set.next()) {
                // 反射
                object = (T) clz.newInstance();
                // 通过列名去找set方法
                for (String cname : cnames) {
                    for (Method method : methods) {
                        if (method.getName().equalsIgnoreCase("set" + cname)) {
                            // 如果找到了直接执行
                            method.invoke(object, set.getObject(cname));
                        }
                    }
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
