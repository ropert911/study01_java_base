package com.study.base.mybatis5.mybatis5;

import java.io.Reader;
import java.util.List;

import com.study.base.mybatis5.mybatis5.maper.GroupMaper;
import com.study.base.mybatis5.mybatis5.maper.UserGroupMaper;
import com.study.base.mybatis5.mybatis5.maper.UserMaper;
import com.study.base.mybatis5.mybatis5.pojo.Group;
import com.study.base.mybatis5.mybatis5.pojo.User;
import com.study.base.mybatis5.mybatis5.pojo.UserGroup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author sk-qianxiao
 */
public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config/Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
         testAddGroup();
         testAddUser();
         testAddUserGroup();
        testGetGroupAndUsers();

    }

    public static void testGetGroupAndUsers() {
        UserGroup userGroup = new UserGroup();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            GroupMaper groupMaper = session.getMapper(GroupMaper.class);
            Group group = groupMaper.getGroup(1);
            System.out.println("Group => " + group.getGroupName());
            List<User> users = group.getUsers();
            for (User user : users) {
                System.out.println("\t:" + user.getId() + "\t"
                        + user.getUsername());
            }
        } finally {
            session.close();
        }
    }

    public static void testAddUserGroup() {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(1);
        userGroup.setUserId(2);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserGroupMaper userGroupMaper = session
                    .getMapper(UserGroupMaper.class);
            userGroupMaper.insertUserGroup(userGroup);

            session.commit();
        } finally {
            session.close();
        }

    }

    public static void testAddUser() {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUsername("User-name-1");
            user.setMobile("13838009988");
            UserMaper userMaper = session.getMapper(UserMaper.class);
            userMaper.insertUser(user);
            session.commit();
            // System.out.println(user.getGroupId());
        } finally {
            session.close();
        }
    }

    public static void testAddGroup() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Group group = new Group();
            group.setGroupName("用户组-1");
            GroupMaper groupMapper = session.getMapper(GroupMaper.class);
            groupMapper.insertGroup(group);
            session.commit();
            System.out.println(group.getGroupId());
        } finally {
            session.close();
        }
    }
}