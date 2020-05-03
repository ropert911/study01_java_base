package com.study.netty.d2_tcp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TcpClient {
    private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);

    private static String HOST = "127.0.0.1";

    private static Bootstrap bootstrap = getBootstrap();
    private static Channel channel = getChannel(HOST, 8080);

    /**
     * 初始化Bootstrap
     */
    private static Bootstrap getBootstrap() {
        try {
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    //写超时，发心跳
                    pipeline.addLast("ping", new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
                    pipeline.addLast("handler", new TcpClientHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            return bootstrap;
        } catch (Exception e) {
            logger.error("error: " + e);
        }
        return null;
    }

    private static Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            logger.error("连接Server(IP{},PORT{})失败", host, port, e);
            return null;
        }
        return channel;
    }

    public static void sendMsg(String msg) {
        try {
            if (channel != null) {
                channel.writeAndFlush(msg).sync();
            } else {
                logger.warn("消息发送失败,连接尚未建立!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TcpClient.sendMsg("你好! ");
    }
}
