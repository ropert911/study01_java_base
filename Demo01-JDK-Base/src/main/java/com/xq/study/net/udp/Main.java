package com.xq.study.net.udp;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) {
        UdpService udpService = new UdpService();
        Thread thread = new Thread(() -> {
            udpService.startServer();
        });
        thread.start();

        String value = "Hello world";
        UdpClient udpClient = new UdpClient();
        String receivedData = udpClient.sendData(value);
        System.out.println(receivedData);

        udpService.setExit(true);
    }
}
