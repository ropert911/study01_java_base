package com.study.base.网络_tcp.d4_tcp_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class NioServer {
    private static Selector selector;
    private static ServerSocketChannel serverSocketChannel;

    public static void initServer(int port) throws IOException {
        //打开一个通道
        serverSocketChannel = ServerSocketChannel.open();
        //通道设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", port));
        //注册
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void listen() throws IOException {
        System.out.println("server started succeed!");

        while (true) {
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                if (key.isAcceptable()) {
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    recvAndReply(key);
                }
                ite.remove();
            }
        }
    }

    public static void recvAndReply(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        int i = channel.read(buffer);
        if (i != -1) {
            String msg = new String(buffer.array()).trim();
            System.out.println("NIO server received message =  " + msg);
            channel.write(ByteBuffer.wrap("Server msg".getBytes()));
        } else {
            channel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        initServer(8080);
        listen();
    }
}
