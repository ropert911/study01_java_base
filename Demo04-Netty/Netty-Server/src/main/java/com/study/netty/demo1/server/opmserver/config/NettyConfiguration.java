package com.study.netty.demo1.server.opmserver.config;

import com.study.netty.demo1.server.opmserver.HttpProtobufInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * Created by sk-ziconglu on 2017/3/9.
 */
@Configuration
@ComponentScan("com.xq.study.nettyserver")
public class NettyConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyConfiguration.class);

    @Value("${sks.nettyclient.boss.thread.count:1}")
    private int bossCount;

    @Value("${sks.nettyclient.worker.thread.count:1}")
    private int workerCount;

    @Value("${sks.netty.hostname:127.0.0.1}")
    private String hostname;

    @Value("${sks.netty.port:3636}")
    private int port;

    @Autowired
    @Qualifier(value = "httpProtobufInitializer")
    private HttpProtobufInitializer httpProtobufInitializer;


    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workerGroup());
        serverBootstrap.channel(NioServerSocketChannel.class);
        
        if (LOGGER.isTraceEnabled()) {
            serverBootstrap.handler(new LoggingHandler(LogLevel.TRACE));
        }
        if (LOGGER.isDebugEnabled()) {
            serverBootstrap.handler(new LoggingHandler(LogLevel.DEBUG));
        }

        //用来监听已经连接的客户端的Channel的动作和状态
        //handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行，这是两者的区别
        serverBootstrap.childHandler(httpProtobufInitializer);
        return serverBootstrap;
    }

    //Acceptor线程池: 接收线程池初始化
    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    //IO线程池
    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount);
    }

    //服务器地址
    @Bean(name = "inetSocketAddress")
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(hostname, port);
    }

}