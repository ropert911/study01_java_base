package com.study.netty.d1_http_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author xq
 * @data 2020/5/3
 **/
public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new HttpServer(8080).start();
    }

    public void start() throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        //监听和读取共用loopGroup
        serverBootstrap.group(group)
                //通道类型是非阻塞tcp类型
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws Exception {
                        System.out.println("initChannel ch:" + ch);
                        ch.pipeline()
                                //用于解码http的请求 -- 字符串生成 HttpMessage
                                .addLast("decoder", new HttpRequestDecoder())
                                //用于编码成http的返回
                                .addLast("encoder", new HttpResponseEncoder())
                                //用于消息聚合，生成 HttpMessage 转 FullHttpRequest
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                //处理FullHttpRequest
                                .addLast("handler", new HttpHandler());
                    }
                })
                // determining the number of connections queued
                .option(ChannelOption.SO_BACKLOG, 128)
                //保活
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);

        serverBootstrap.bind(port).sync();
    }
}