package com.study.netty.server.opmserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * Created by sk-ziconglu on 2017/3/9.
 */
@Component
public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    private ChannelFuture serverChannelFuture;

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;

    @Autowired
    @Qualifier("inetSocketAddress")
    private InetSocketAddress inetSocketAddress;

    @PostConstruct	//初始bean的初始化函数
    public void start() throws Exception {
        LOGGER.info("Starting server at {}", inetSocketAddress);
        serverChannelFuture = serverBootstrap.bind(inetSocketAddress).sync();
    }

    @PreDestroy		//销毁bean前的函数
    public void stop() throws Exception {
        serverChannelFuture.channel().close();
    }
}
