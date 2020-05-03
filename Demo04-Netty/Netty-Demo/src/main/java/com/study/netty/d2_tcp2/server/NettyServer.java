package com.study.netty.d2_tcp.server.server3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {
    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int BIZTHREADSIZE = 100;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    //服务端
    static ServerBootstrap serverBootstrap;

    public static void start() {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        //添加handler监听服务端的IO动作
        serverBootstrap.handler(new OutHandler());
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel arg0)
                    throws Exception {
                // TODO Auto-generated method stub
                ChannelPipeline pipeline = arg0.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                //添加handler监听客户端Channel的状态变化
                pipeline.addLast(new TcpServerHandler2());
            }
        });

        try {
            //服务端启动
            ChannelFuture cf = serverBootstrap.bind("127.0.0.1", 8080).sync();
//            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        start();
    }
}