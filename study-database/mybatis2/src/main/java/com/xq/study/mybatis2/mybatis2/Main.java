package com.xq.study.mybatis2.mybatis2;

import com.xq.study.mybatis2.mybatis2.dao.IUser;
import com.xq.study.mybatis2.mybatis2.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by xq on 2018/7/29.
 */

public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config/Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUser iuser = session.getMapper(IUser.class);
            User user = iuser.getUserById(1);
            System.out.println("名字：" + user.getName());
            System.out.println("所属部门：" + user.getDept());
            System.out.println("主页：" + user.getWebsite());
        } finally {
            session.close();
        }
    }
}