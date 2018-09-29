package com.custom.mybatis.factory;

import java.io.InputStream;

/**
 * 构建者模式创建SqlSessionFactory
 *
 * @author Admin
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory builder() {
        // 默认方式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        InputStream config = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory.setConfig(config);
        return sqlSessionFactory;
    }

    public SqlSessionFactory builder(String fileName) {

        // 读取可以自定义命名
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        InputStream config = this.getClass().getClassLoader().getResourceAsStream(fileName);
        sqlSessionFactory.setConfig(config);
        return sqlSessionFactory;

    }

    public SqlSessionFactory builder(InputStream config) {

        // 读取不用位置的xml
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        sqlSessionFactory.setConfig(config);
        return sqlSessionFactory;
    }



}
