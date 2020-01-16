package com.study.base.nettyserver;

import com.study.netty.server.serverhttp.HttpServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyserverApplicationTests {
    private static Log log = LogFactory.getLog(NettyserverApplicationTests.class);

    @Test
    public void contextLoads() {
        try {
            log.info("Http Server listening on 3636 ...");
            HttpServer server = new HttpServer();
            server.start(3636);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
