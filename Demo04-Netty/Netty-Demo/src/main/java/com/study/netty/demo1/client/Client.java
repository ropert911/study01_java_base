package com.study.netty.demo1.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 3; i++) {
                TcpClient.sendMsg("你好! " + i);
            }
        } catch (Exception e) {
            logger.error("main err:", e);
        }
    }
}
