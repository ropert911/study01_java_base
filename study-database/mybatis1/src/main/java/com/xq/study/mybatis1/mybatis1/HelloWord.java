package com.xq.study.mybatis1.mybatis1;

import com.xq.study.mybatis1.mybatis1.dao.IUser;
import com.xq.study.mybatis1.mybatis1.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

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
            sqlSessionFactory.getConfiguration().addMapper(IUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //方法一：从xml中加载的
            User user1 = session.selectOne("models.UserMapper.GetUserByID", 1);
            if (user1 != null) {
                printUser(user1);
            }
            //方法二：从IUSer中加载的
            IUser iuser = session.getMapper(IUser.class);
            User user2 = iuser.getUserById(2);
            if (user2 != null) {
                printUser(user2);
            }
        } finally {
            session.close();
        }
    }

    private static void printUser(User user) {
        System.out.println("名字：" + user.getName());
        System.out.println("所属部门：" + user.getDept());
        System.out.println("主页：" + user.getWebsite());
    }

}
