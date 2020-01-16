package com.study.db.mybatis4;

import com.study.db.mybatis4.pojo.Post;
import com.study.db.mybatis4.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

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
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int userid = 1;
            System.out.println("===========getUser");
            User user = session.selectOne("user.userMaper.getUser", 1);
            System.out.println("username: " + user.getUsername() + ",");

            List<Post> posts = user.getPosts();
            for (Post p : posts) {
                System.out.println("Title:" + p.getTitle());
                System.out.println("Content:" + p.getContent());
            }

            int postId = 1;
            System.out.println("\n===========getPosts");
            Post post = session.selectOne("user.userMaper.getPosts", postId);
            System.out.println("title: " + post.getTitle());
            System.out.println("userName: " + post.getUser().getUsername());
        } finally {
            session.close();
        }
    }

}