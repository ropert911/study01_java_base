package com.study.netty.d2_tcp_ssl_single.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
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
import java.util.concurrent.TimeUnit;

public class TcpClient {
    private static final Logger logger = LoggerFactory.getLogger(TcpClient.class);
    private static Bootstrap bootstrap = null;
    private static Channel channel;


    private static SSLEngine getSSLEngine(ByteBufAllocator alloc) throws IOException {
//        InputStream server_crt = new ClassPathResource("client.crt").getInputStream();
//        InputStream server_pkcs8 = new ClassPathResource("client.pkcs8").getInputStream();
        InputStream server_crt = new ClassPathResource("server.crt").getInputStream();
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(server_crt).build();

        SSLEngine sslEngine = sslContext.newEngine(alloc);
        //客户端格式
        sslEngine.setUseClientMode(true);
        //需要验证客户端
        sslEngine.setNeedClientAuth(true);

        return sslEngine;
    }

    private static Bootstrap getBootstrap() {
        //ssl
        try {
//            KeyStore ks = KeyStore.getInstance("JKS");
//            InputStream ksInputStream = TcpClient.class.getResourceAsStream("/cChat.jks");
//            ks.load(ksInputStream, "cNetty".toCharArray());
//            TrustManagerFactory tf = TrustManagerFactory.getInstance("SunX509");
//            tf.init(ks);
//
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, tf.getTrustManagers(), null);
//            final SSLEngine sslEngine = sslContext.createSSLEngine();
//
//            sslEngine.setUseClientMode(true); //服务器端模式
////            sslEngine.setNeedClientAuth(true); //不需要验证客户端

            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    SSLEngine sslEngine = getSSLEngine(ch.alloc());

                    pipeline.addFirst("ssl", new SslHandler(sslEngine));
                    ;
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast("ping", new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
                    pipeline.addLast("handler", new TcpClientHandler());
                }
            });
            b.option(ChannelOption.SO_KEEPALIVE, true);
            return b;
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

    private static void sendMsg(String msg) {
        try {
            if (channel != null) {
                channel.writeAndFlush(msg).sync();
            } else {
                logger.warn("消息发送失败,连接尚未建立!");
            }
        } catch (Exception e) {
            logger.error("main err:", e);
        }
    }

    public static void main(String[] args) {
        bootstrap = getBootstrap();
        channel = getChannel("127.0.0.1", 8080);
        TcpClient.sendMsg("你好!");
    }
}
