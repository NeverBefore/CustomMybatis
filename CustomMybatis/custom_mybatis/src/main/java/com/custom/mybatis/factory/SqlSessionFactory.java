package com.custom.mybatis.factory;

import com.custom.mybatis.core.SqlSession;
import com.custom.mybatis.core.impl.SqlSessionImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 帮助sqlSession创建实现类
 * 读取xml文件，完成封装
 *
 * @author Admin
 */
public class SqlSessionFactory {

    private InputStream config;

    public void setConfig(InputStream config) {
        this.config = config;
    }

    public SqlSession openSession() {
        SqlSessionImpl sqlSession = new SqlSessionImpl();

        sqlSession = readXMLConfig(sqlSession);

        return sqlSession;
    }

    private SqlSessionImpl readXMLConfig(SqlSessionImpl sqlSession) {

        // 读取xml
        try {
            Document document = new SAXReader().read(config);
            // 读取目录
            Element root = document.getRootElement();
            List<Element> list = root.selectNodes("//property");
            for (Element element : list) {
                // 获取name value
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                switch (name) {
                    case "driver":
                        sqlSession.setDriver(value);
                        break;
                    case "url":
                        sqlSession.setUrl(value);
                        break;
                    case "username":
                        sqlSession.setUsername(value);
                        break;
                    case "password":
                        sqlSession.setPassword(value);
                        break;
                    default:
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
