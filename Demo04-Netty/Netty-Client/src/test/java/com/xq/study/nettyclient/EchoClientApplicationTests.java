package com.xq.study.nettyclient;

import com.xq.study.nettyclient.clientecho.EchoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EchoClientApplicationTests {

    @Test
    public void contextLoads() {
        try {
            new EchoClient("127.0.0.1", 3636).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
