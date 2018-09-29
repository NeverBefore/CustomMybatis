package com.custom.dao;

import com.custom.entity.UserInfo;
import com.custom.mybatis.ann.Select;

import java.util.List;

/**
 * 不需要此接口有实现类同样去执行findAll方法
 *
 * @author Admin
 */
public interface NoUserDaoImpl {

    /**
     * 查询全部
     *
     * @return
     * @throws Exception
     */
    @Select(value = "select * from user")
    List<UserInfo> findAll() throws Exception;
}
