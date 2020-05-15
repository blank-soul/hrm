package com.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis工具类
 * Create by HP on 2020/5/11
 * 游魂
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    private MyBatisUtil(){
    }

    static{
        InputStream inputStream = null;
        try {
            // 读取全局配置文件
            inputStream = Resources.getResourceAsStream("MyBatisConfig.xml");
            // 创建session工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = threadLocal.get();
        if(null == sqlSession){
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void close(){
        SqlSession sqlSession = threadLocal.get();
        if(null != sqlSession){
            sqlSession.commit();
            sqlSession.close();
            threadLocal.remove();
        }
    }

    public static <T>T getMapper(Class cls){
        return (T) getSqlSession().getMapper(cls);
    }
}
