package com.study.netty.d2_tcp_ssl_single.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);

    /**
     * 建立连接处理
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("连接建立，channel id ：" + ctx.channel().id());
    }

    /**
     * 接收到消息处理
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("收到==>{}", msg);
        ctx.channel().writeAndFlush(msg + " response");
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("exceptionCaught!", cause);
        ctx.close();
    }

    /**
     * 心跳机制处理
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                logger.info("read idle");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                logger.info("write idle");
            } else if (event.state() == IdleState.ALL_IDLE) {
                logger.info("all idle");
            }
        }
    }

    /**
     * 断开连接处理
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("连接断开，channel id ：" + ctx.channel().id());
    }
}
