package com.study.spring.mybatis.annotation;

import com.study.spring.mybatis.annotation.service.UserService;
import com.study.spring.mybatis.annotation.entity.UserEntity;
import com.study.spring.mybatis.annotation.entity.UserSexEnum;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.study.spring.mybatis.annotation.mapper")
public class SpringMybatisApplication implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(SpringMybatisApplication.class);

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        UserEntity userEntity = UserEntity.builder().id(1L).nickName("疯子").passWord("secrit").userName("xiaoqian").userSex(UserSexEnum.MAN).build();
        userService.save(userEntity);

        UserEntity uget = userService.getUser(1L);
        logger.info("get user[{}]=={}", userEntity.getId(),uget);

        uget.setNickName("new_neck");
        userService.update(uget);

        uget = userService.getUser(uget.getId());
        logger.info("get user[1]=={}", uget);

        List<UserEntity> users = userService.getUsers();
        for (UserEntity user:users){
            logger.info("{}", uget);
            userService.delete(user.getId());
        }

    }
}
