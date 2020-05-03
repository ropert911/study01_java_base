package com.study.netty.demo1.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args){
        logger.info("启动TCP服务器...");
        TcpServer.run();
        // TcpServer.shutdown();
    }
}
