package com.study.base.nettyserver;

import com.study.netty.server.server3.NettyServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Server3ApplicationTests {
    private static Log log = LogFactory.getLog(Server3ApplicationTests.class);

    @Test
    public void contextLoads() {
        try {
            log.info("Http Server listening on 3636 ...");
            new NettyServer().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
