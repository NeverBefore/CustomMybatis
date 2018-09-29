package com.custom.test;

import com.custom.dao.NoUserDaoImpl;
import com.custom.entity.UserInfo;
import com.custom.mybatis.core.SqlSession;
import com.custom.mybatis.factory.SqlSessionFactory;
import com.custom.mybatis.factory.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class MybatisTest {

    @Test
    public void test() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.builder();
        SqlSession sqlSession = factory.openSession();
        try {
            NoUserDaoImpl userDao = sqlSession.getDaoImpl(NoUserDaoImpl.class);
            List<UserInfo> list = userDao.findAll();
            for (UserInfo userInfo : list) {
                System.out.println(userInfo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
