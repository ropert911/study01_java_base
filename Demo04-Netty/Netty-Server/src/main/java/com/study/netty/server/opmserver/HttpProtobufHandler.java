package com.study.netty.server.opmserver;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Created by sk-ziconglu on 2017/3/9.
 */
public class HttpProtobufHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProtobufHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        FullHttpResponse response;

        //发送：curl -v -X POST --retry 3  --retry-delay 10 --retry-max-time 900 --data-binary @abc http://10.10.23.13:3636/arrow/iops-collector-api:insertSksinfdfsafo >/dev/null
        try {
            URI uri = new URI(fullHttpRequest.uri());

            byte[] bytes = new byte[fullHttpRequest.content().readableBytes()];
            fullHttpRequest.content().readBytes(bytes);
            String con = new String(bytes);
            System.out.println("=========" + uri.toString() + "================");
            System.out.println(con);

            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        } catch (Throwable cause) {
            LOGGER.error("Fail to send message", cause);
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }

        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("Exception caught:", cause);
        ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST)).addListener(ChannelFutureListener.CLOSE);
    }

}
