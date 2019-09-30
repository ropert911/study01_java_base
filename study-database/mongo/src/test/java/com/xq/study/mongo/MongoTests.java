package com.xq.study.mongo;

import com.xq.study.mongo.constans.Sex;
import com.xq.study.mongo.dao.UserDao;
import com.xq.study.mongo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTests {
    private static final Logger logger = LoggerFactory.getLogger(MongoTests.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void test1_SaveUser() throws Exception {
        User user = new User();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        user.setSex(Sex.MALE);
        userDao.saveUser(user);
        logger.info("保存一个用户： {}", user);
        user = userDao.findUserByUserName("小明");
        logger.info("查找用户小明1： {} ", user);
    }


    @Test
    public void test3_updateUser() {
        User user = new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDao.updateUser(user);
        logger.info("");
        logger.info("更新用户为天空： {} ", user);
        user = userDao.findUserByUserName("天空");
        logger.info("更新后查找用户天空： {} ", user);
    }


    @Test
    public void test5_deleteUserById() {
        User user = userDao.findUserByUserName("天空");
        userDao.deleteUserById(user.getId());
        logger.info("");
        logger.info("删除天空");
        user = userDao.findUserByUserName("天空");
        logger.info("删除后查找用户天空： {} ", user);
    }
}
