package com.study.db.mybatis1;

import com.study.db.mybatis1.mapper.IUser;
import com.study.db.mybatis1.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author sk-qianxiao
 */
public class HelloWord {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config/Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //如果xml中已经有IUser的Namespace，就不用添加mapper
//            sqlSessionFactory.getConfiguration().addMapper(IUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        UserTest.test(session);
    }



}
