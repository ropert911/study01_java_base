package com.study.netty.d2_tcp.server.server3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

public class OutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void connect(ChannelHandlerContext ctx,
                        SocketAddress remoteAddress, SocketAddress localAddress,
                        ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
        System.out.println("<<<<<<<<<<<<<<< connect server success >>>>>>>>>>>>>>>>");
    }

    @Override
    public void bind(ChannelHandlerContext ctx,
                     SocketAddress localAddress, ChannelPromise promise)
            throws Exception {
        super.bind(ctx, localAddress, promise);
        System.out.println("<<<<<<<<<<<<<<< server bind success >>>>>>>>>>>>>>>>");
    }
}