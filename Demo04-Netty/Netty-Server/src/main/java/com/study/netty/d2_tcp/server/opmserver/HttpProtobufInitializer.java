package com.study.netty.d2_tcp.server.opmserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//import org.springframework.kafka.core.KafkaTemplate;
//import com.skspruce.nms.data.collector.KafkaProducerConfiguration;
//import com.skspruce.nms.data.collector.nettyclient.handlers.HttpProtobufHandler;

/**
 * Created by sk-ziconglu on 2017/3/10.
 */
@Component
@Qualifier("httpProtobufInitializer")
public class HttpProtobufInitializer extends ChannelInitializer<SocketChannel> {
    //最大长度32M
    private static final int MAX_CONTENT_LENGTH = 32 * 1024 * 1024;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(MAX_CONTENT_LENGTH))
                .addLast(new HttpProtobufHandler());
    }
}
