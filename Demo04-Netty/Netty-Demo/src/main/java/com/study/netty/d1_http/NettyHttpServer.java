package com.study.netty.d1_http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NettyHttpServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);

    static ChannelFuture channelFuture = null;

    public static void start() {
        try {
            NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);

            if (logger.isTraceEnabled()) {
                serverBootstrap.handler(new LoggingHandler(LogLevel.TRACE));
            }
            if (logger.isDebugEnabled()) {
                serverBootstrap.handler(new LoggingHandler(LogLevel.DEBUG));
            }

            //用来监听已经连接的客户端的Channel的动作和状态
            //handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行，这是两者的区别
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline()
                            .addLast(new HttpServerCodec())
                            //最大32M
                            .addLast(new HttpObjectAggregator(32 * 1024 * 1024))
                            .addLast(new HttpProtobufHandler());
                }
            });
            channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 8080)).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        channelFuture.channel().close();
    }

    public static void main(String[] args) {
        NettyHttpServer.start();
    }
}