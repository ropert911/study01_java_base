package com.study.db.mybatis.mutidatasource;

import com.study.db.mybatis.mutidatasource.service.User1Service;
import com.study.db.mybatis.mutidatasource.service.User2Service;
import com.study.db.mybatis.mutidatasource.entity.UserEntity;
import com.study.db.mybatis.mutidatasource.entity.UserSexEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
public class MutiDsApplication implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(MutiDsApplication.class);

    @Autowired
    User1Service user1Service;

    @Autowired
    User2Service user2Service;

    public static void main(String[] args) {
        SpringApplication.run(MutiDsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        test1();
        logger.info("----------------------");
        test2();
    }
    public void test1(){
        UserEntity userEntity = UserEntity.builder().id(1L).nickName("疯子").passWord("secrit").userName("xiaoqian").userSex(UserSexEnum.MAN).build();
        user1Service.save(userEntity);

        UserEntity uget = user1Service.getUser(1L);
        logger.info("get user[{}]=={}", userEntity.getId(),uget);

        uget.setNickName("new_neck");
        user1Service.update(uget);

        uget = user1Service.getUser(uget.getId());
        logger.info("get user[1]=={}", uget);

        List<UserEntity> users = user1Service.getUsers();
        for (UserEntity user:users){
            logger.info("{}", uget);
            user1Service.delete(user.getId());
        }
    }
    public void test2(){
        UserEntity userEntity = UserEntity.builder().id(2L).nickName("疯子2").passWord("secrit").userName("xiaoqian2").userSex(UserSexEnum.MAN).build();
        user2Service.save(userEntity);

        UserEntity uget = user2Service.getUser(2L);
        logger.info("get user[{}]=={}", userEntity.getId(),uget);

        uget.setNickName("new_neck");
        user2Service.update(uget);

        uget = user2Service.getUser(uget.getId());
        logger.info("get user[1]=={}", uget);

        List<UserEntity> users = user2Service.getUsers();
        for (UserEntity user:users){
            logger.info("{}", uget);
            user2Service.delete(user.getId());
        }
    }
}
