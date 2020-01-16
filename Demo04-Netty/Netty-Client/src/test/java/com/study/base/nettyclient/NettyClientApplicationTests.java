package com.study.base.nettyclient;

import com.study.netty.client.clienthttp.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyClientApplicationTests {

    @Test
    public void contextLoads() {
        try {
            HttpClient client = new HttpClient();
            client.connect("127.0.0.1", 3636);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
