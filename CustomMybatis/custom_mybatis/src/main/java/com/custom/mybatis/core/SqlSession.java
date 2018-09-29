package com.custom.mybatis.core;

/**
 * sql会话，核心接口
 *
 * @author Admin
 */
public interface SqlSession {

    /**
     * 动态获取某个接口的实现类
     *  1.创建某个接口的对象
     *  2.实现接口中的方法
     *
     * @param clz 需要实现的接口
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T getDaoImpl(Class clz) throws Exception;
}
