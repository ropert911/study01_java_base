package com.study.kafka.producer.simple1;

import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by sk-qianxiao on 2017/11/9.
 */
@Component
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    Config config;

    //单个单个发送
    public void sendData(byte[] bytes) {
        String topic = config.getTopic();

        ListenableFuture<SendResult<Bytes, Bytes>> future = config.getKafkaTemplate().send(topic,  Bytes.wrap(bytes));
        future.addCallback(new ListenableFutureCallback<SendResult<Bytes, Bytes>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn("发送失败", ex);
            }

            @Override
            public void onSuccess(SendResult<Bytes, Bytes> result) {
                LOGGER.warn("发送成功. topic:{}, partitions:{}, offset:{} timestamp:{}",
                        topic, result.getRecordMetadata().partition(), result.getRecordMetadata().offset(),result.getRecordMetadata().timestamp());
            }
        });
        config.getKafkaTemplate().flush();
    }
}
