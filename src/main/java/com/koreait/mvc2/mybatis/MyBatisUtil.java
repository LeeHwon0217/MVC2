package com.koreait.mvc2.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}