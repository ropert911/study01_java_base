package com.study.base.net.d1_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author sk-qianxiao
 */
public class UdpClient {
    public String sendData(String messages) {
        String receivedData = new String();
        byte[] bys = messages.getBytes();
        try {
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("127.0.0.1"), UdpService.PORT);
            ds.send(dp);

            DatagramPacket received = new DatagramPacket(new byte[1024], 1024);
            ds.receive(received);
            receivedData = new String(received.getData(), 0, received.getLength());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedData;
    }

    public static void main(String[] args) {
        String value = "Hello world";
        UdpClient udpClient = new UdpClient();
        String receivedData = udpClient.sendData(value);
        System.out.println(receivedData);
    }
}
