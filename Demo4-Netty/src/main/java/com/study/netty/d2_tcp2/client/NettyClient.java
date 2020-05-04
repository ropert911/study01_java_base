package com.study.netty.d2_tcp.client.client3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyClient {
    EventLoopGroup group = new NioEventLoopGroup();
    Channel serverChannel;

    public void init() {
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                //添加一个Hanlder用来处理各种Channel状态
                pipeline.addLast("handlerIn", new ClientHandler());
                //添加一个Handler用来接收监听IO操作的
                pipeline.addLast("handlerOut", new OutHandler());
            }
        });
        ChannelFuture f;
        try {
            //连接服务端
            f = b.connect("127.0.0.1", 8080).sync();
            serverChannel = f.channel();
            serverChannel.writeAndFlush("<<<<<<<<<<<<<<<<client msg>>>>>>>>>>>>>>>>");

            serverChannel.flush();
            serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        try {
            NettyClient client = new NettyClient();
            client.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}