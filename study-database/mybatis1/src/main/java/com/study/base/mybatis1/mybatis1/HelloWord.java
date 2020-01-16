package com.study.base.mybatis1.mybatis1;

import com.study.base.mybatis1.mybatis1.dao.IUser;
import com.study.base.mybatis1.mybatis1.models.User;
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
            user2 = iuser.getUser(2);
            if (user2 != null) {
                printUser(user2);
            }

            // 用户数据列表
            getUserList(session, iuser);

            // 插入数据
            testInsert(session, iuser);

            // 更新用户
            testUpdate(session, iuser);

            // 删除数据
            testDelete(session, iuser);
        } finally {
            session.close();
        }

    }


    /**
     * 插入用户
     */
    public static void testInsert(SqlSession session, IUser iuser) {
        try {
            System.out.println("Test insert start...");
            // 执行插入
            User user = new User();
            user.setId(0);
            user.setName("Google");
            user.setDept("Tech");
            user.setWebsite("http://www.google.com");
            user.setPhone("120");
            iuser.insertUser(user);
            // 提交事务
            session.commit();

            // 显示插入之后User信息
            System.out.println("After insert");
            getUserList(session, iuser);
            System.out.println("Test insert finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户列表
     */
    public static void getUserList(SqlSession session, IUser iuser) {
        try {
            System.out.println("Test Get start...");
            printUsers(iuser.getUserList());
            System.out.println("Test Get finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate(SqlSession session, IUser iuser) {
        try {
            System.out.println("Test update start...");
            printUsers(iuser.getUserList());
            // 执行更新
            User user = iuser.getUser(1);
            user.setName("New name");
            iuser.updateUser(user);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printUsers(iuser.getUserList());
            System.out.println("Test update finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除用户信息
     */
    public static void testDelete(SqlSession session, IUser iuser) {
        try {
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            List<User> userList = iuser.getUserList();
            for (User user : userList) {
                iuser.deleteUser(user.getId());
            }

            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
            printUsers(iuser.getUserList());
            System.out.println("Test delete finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印用户信息到控制台
     *
     * @param users
     */
    private static void printUsers(final List<User> users) {
        int count = 0;

        for (User user : users) {
            System.out.println(MessageFormat.format(
                    "============= User[{0}]=================", ++count));
            printUser(user);
        }
    }

    private static void printUser(User user) {
        System.out.println(MessageFormat.format("{0}_{1}_{2}_{3}", user.getId(), user.getName(), user.getDept(), user.getWebsite()));
    }
}
