package com.study.ovsdb.demo3_sslwithfile;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SslServer.init();
        SslClient.init();
    }
}
