package com.study.netty.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.InputStream;
import java.security.KeyStore;


/**
 * Created by sk-cailicheng on 2017/11/21.
 */
public class TcpServer {
    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8989;

    //用于分配处理业务线程的线程组个数
    private static final int GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认
    //业务出现线程大小
    private static final int THREAD_SIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(GROUP_SIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(THREAD_SIZE);

    public static void run() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frame1Decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frame1Encoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder1", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder1", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new IdleStateHandler(10, 0, 0), new TcpServerHandler());
            }
        });

        try {
            serverBootstrap.bind(IP, PORT).sync();
            logger.info("TCP服务器已启动");
        } catch (InterruptedException e) {

        }
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
