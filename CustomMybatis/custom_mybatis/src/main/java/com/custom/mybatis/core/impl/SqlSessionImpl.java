package com.custom.mybatis.core.impl;

import com.custom.mybatis.core.SqlSession;
import com.custom.mybatis.factory.DaoImplProxyFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * sqlSession的实现类
 *
 * @author Admin
 */
public class SqlSessionImpl implements SqlSession {

    private String driver;
    private String url;
    private String username;
    private String password;

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Connection conn;

    public <T> T getDaoImpl(Class clz) throws Exception {

        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);

        // 代理模式
        /**
         * 需要三个参数
         * 1.被代理对象
         * 2.被代理对象实现类实现的接口
         * 3.代理功能增强的帮助类
         */
        return (T) Proxy.newProxyInstance(clz.getClassLoader(),new Class[] {clz} ,new DaoImplProxyFactory());
    }
}
