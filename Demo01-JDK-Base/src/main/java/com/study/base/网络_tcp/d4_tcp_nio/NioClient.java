package com.study.base.网络_tcp.d4_tcp_nio;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class NioClient {
    private static SocketChannel channel;

    public static void initClient(String host, int port) throws IOException {
        InetSocketAddress servAddr = new InetSocketAddress(host, port);
        channel = SocketChannel.open(servAddr);
    }

    public static void sendAndRecv(String words) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(words.getBytes());
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        System.out.println("Client received: " + new String(buffer.array()).trim());
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        initClient("localhost", 8080);
        sendAndRecv("Client message");
    }
}
