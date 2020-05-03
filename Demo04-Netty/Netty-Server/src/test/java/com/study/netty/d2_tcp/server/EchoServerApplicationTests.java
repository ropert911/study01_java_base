package com.study.netty.d2_tcp.server;

import com.study.netty.d2_tcp.server.serverecho.EchoServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EchoServerApplicationTests {
    private static Log log = LogFactory.getLog(EchoServerApplicationTests.class);

    @Test
    public void contextLoads() {
        try {
            log.info("Http com.study.demo1jsonrpc.Server listening on 3636 ...");
            new EchoServer(3636).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
