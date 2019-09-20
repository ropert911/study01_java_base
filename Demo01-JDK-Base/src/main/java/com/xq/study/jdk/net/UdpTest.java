package com.xq.study.jdk.net;

import com.xq.study.jdk.compress.GZipUtil;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class UdpTest {
    public static void main(String arg[]) {
        List<String> messages = new ArrayList<>();
        try {
            DatagramSocket ds = new DatagramSocket();
            StringBuilder sb = new StringBuilder();
            for (String msg : messages) {
                sb.append(msg);
            }
            byte[] bys = GZipUtil.compress(sb.toString().getBytes());
            java.net.DatagramPacket dp = new java.net.DatagramPacket(bys, bys.length,
                    InetAddress.getByName("127.0.0.1"), 344);
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
