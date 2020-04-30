package com.study.ovsdb.demo3_sslwithfile;

import com.study.ovsdb.clientEmulator.SslUtil;

import static com.study.ovsdb.clientEmulator.SslUtil.newSelfSignedSslContextPair;

/**
 * @author sk-qianxiao
 * @date 2020/4/30
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SslUtil.SelfSignedSslContextPair sslContextPair = newSelfSignedSslContextPair();
        SslServer.init();
        SslClient.init();
    }
}
