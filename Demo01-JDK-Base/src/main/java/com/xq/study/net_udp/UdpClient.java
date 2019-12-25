package com.xq.study.net_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author sk-qianxiao
 */
public class UdpClient {
    public String sendData(String messages) {
        String rData = new String();
        byte[] bys = messages.getBytes();
        try {
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("127.0.0.1"), UdpService.PORT);
            ds.send(dp);
            DatagramPacket received = new DatagramPacket(new byte[1024], 1024);
            ds.receive(received);
            rData = new String(received.getData(), 0, received.getLength());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rData;
    }
}
