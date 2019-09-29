package com.xq.study.anocation;

import com.xq.study.anocation.aop.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTests {

    @Autowired
    MyUser user;

    @Test
    public void contextLoads() {
        try {
            user.displayName();
        } catch (Exception e) {
            System.out.println("抛出异常");
        }
    }
}
