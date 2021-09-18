package com.study.base.网络_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author sk-qianxiao
 */
public class UdpService {
    public final static int PORT = 1080;
    private boolean exit = false;

    public void startServer() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (!exit) {
                try {
                    /**获取发送端dp*/
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    /**发送返回值*/
                    DatagramPacket response = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                    socket.send(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UdpService udpService = new UdpService();
        udpService.startServer();
    }
}
