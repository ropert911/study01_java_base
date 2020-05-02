package com.study.db.mybatis1;

import com.study.db.mybatis1.mapper.IUser;
import com.study.db.mybatis1.models.User;
import org.apache.ibatis.session.SqlSession;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author xq
 * @data 2020/5/2
 **/
public class UserTest {
    public static void test(SqlSession session) {
        try {
            //方法一：从xml中加载的
            User user1 = session.selectOne("com.study.db.mybatis1.mapper.IUser.GetUserByID_xml", 1);
            printUser(user1);

            //方法二：从IUSer中加载的
            IUser iuser = session.getMapper(IUser.class);
            User user2 = iuser.getUser(2);
            List<User> userAll = iuser.getUserList();

            printUser(user1);
            printUser(user2);
            printUsers(userAll);

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
            // 执行插入
            User user = new User();
            user.setName("Google");
            user.setDept("Tech");
            user.setWebsite("http://www.google.com");
            user.setPhone("120");
            iuser.insertUser(user);
            // 提交事务
            session.commit();


            // 显示插入之后User信息
            List<User> users = iuser.getUserList();
            System.out.println("新插入用户后的列表-----");
            printUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate(SqlSession session, IUser iuser) {
        try {
            List<User> users = iuser.getUserList();
            for (User user : users) {
                if (user.getId() > 2) {
                    user.setName("New name");
                    iuser.updateUser(user);
                }
            }
            session.commit();

            // 显示更新之后User信息
            users = iuser.getUserList();
            System.out.println("更新了用户Id3后的用户列表");
            printUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDelete(SqlSession session, IUser iuser) {
        try {
            List<User> users = iuser.getUserList();
            for (User user : users) {
                if (user.getId() > 2) {
                    iuser.deleteUser(user.getId());
                }
            }

            session.commit();

            // 显示删除之后User信息
            users = iuser.getUserList();
            System.out.println("删除用户3后的用户列表----------");
            printUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void printUsers(final List<User> users) {
        int count = 0;

        for (User user : users) {
            System.out.println(MessageFormat.format("============= User[{0}]=================", ++count));
            printUser(user);
        }
    }

    private static void printUser(User user) {
        System.out.println(MessageFormat.format("{0}_{1}_{2}_{3}", user.getId(), user.getName(), user.getDept(), user.getWebsite()));
    }
}
