package com.study.netty.demo1.client;

import com.study.netty.demo1.client.client3.NettyClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Client3ApplicationTests {

    @Test
    public void contextLoads() {
        try {
            NettyClient client = new NettyClient();
            client.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
