package com.study.netty.d2_tcp_ssl.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
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
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLEngine;
import java.io.IOException;
import java.io.InputStream;


public class TcpServer {
    private static final Logger logger = LoggerFactory.getLogger(TcpServer.class);

    private static final String IP = "127.0.0.1";
    private static final int GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认
    private static final int THREAD_SIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(GROUP_SIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(THREAD_SIZE);

    private static SSLEngine getSSLEngine(ByteBufAllocator alloc) throws IOException {
        InputStream server_crt = new ClassPathResource("server.crt").getInputStream();
        InputStream server_pkcs8 = new ClassPathResource("server.pkcs8").getInputStream();
        InputStream client_crt = new ClassPathResource("client.crt").getInputStream();
        SslContext sslContext = SslContextBuilder.forServer(server_crt, server_pkcs8)
                .trustManager(client_crt).build();

        SSLEngine sslEngine = sslContext.newEngine(alloc);
        //服务器端模式
        sslEngine.setUseClientMode(false);
        //需要验证客户端
        sslEngine.setNeedClientAuth(true);

        return sslEngine;
    }

    private static void run(int port) throws Exception {
        //ssl
//        KeyStore ks = KeyStore.getInstance("JKS");
//        InputStream ksInputStream = TcpServer.class.getResourceAsStream("/sChat.jks");
//        ks.load(ksInputStream, "sNetty".toCharArray());
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//        kmf.init(ks, "sNetty".toCharArray());
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), null, null);
//
//        final SSLEngine sslEngine = sslContext.createSSLEngine();
//        sslEngine.setUseClientMode(false); //服务器端模式
//        sslEngine.setNeedClientAuth(false); //不需要验证客户端


        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                SSLEngine sslEngine = getSSLEngine(ch.alloc());

                pipeline.addFirst("ssl", new SslHandler(sslEngine));
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new IdleStateHandler(10, 0, 0), new TcpServerHandler());
            }
        });

        b.bind(IP, port).sync();
        logger.info("TCP服务器已启动");
    }


    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        logger.info("启动TCP服务器...");
        TcpServer.run(8080);
        // TcpServer.shutdown();
    }
}
