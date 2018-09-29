package com.custom.mybatis.factory;

import com.custom.mybatis.ann.Select;
import com.custom.mybatis.core.impl.SqlSessionImpl;
import com.custom.mybatis.util.MybatisUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 工厂设计模式
 *
 * @author Admin
 */
public class DaoImplProxyFactory implements InvocationHandler{

    /**
     * 被代理对象的方法增强功能
     *
     * @param proxy 代理对象
     * @param method 要执行的方法
     * @param args  方法参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        // 代理对象的方法增强
        // 1.找到findAll方法
        if (method.isAnnotationPresent(Select.class)) {
            // 获取这个注解
            Select select = method.getAnnotation(Select.class);
            String sql = select.value();
            // sql数据库连接
            Connection conn = SqlSessionImpl.conn;
            // 执行
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet set = ps.executeQuery();
            // 获取方法的返回值 java.util.List<com.custom.entity.UserInfo>
            String returnType = method.getGenericReturnType().toString();
            if (returnType.startsWith(List.class.getName())) {
                // 将java.util.List替换为""
                returnType = returnType.replace(List.class.getName(), "");
                // 将<com.custom.entity.UserInfo>的<>替换为""
                returnType = returnType.replace("<", "");
                returnType = returnType.replace(">", "");
                // com.custom.entity.UserInfo
            }
            result = MybatisUtil.getListByResult(set, returnType);
        }
        return result;
    }
}
