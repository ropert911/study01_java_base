package com.xq.study.jdk.net.udp;

import org.junit.Assert;
import org.junit.Test;

public class UdpTest {
    @Test
    public void test1() {
        UdpService udpService = new UdpService();
        Thread thread = new Thread(() -> {
            udpService.startServer();
        });
        thread.start();

        String value = "Hello world";
        UdpClient udpClient = new UdpClient();
        String receivedData = udpClient.sendData(value);
        Assert.assertEquals(value, receivedData);

        udpService.setExit(true);
    }
}
