package com.study.kafka.customer.service;

import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by sk-qianxiao on 2017/11/9.
 */
@Service
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Value("${kafka.test.topic.testA}")
    private String topicA;
    @Value("${kafka.test.topic.testB}")
    private String topicB;
    @Value("${kafka.test.topic.testC}")
    private String topicC;
    @Value("${kafka.test.topic.testD}")
    private String topicD;

    static int index = 1;

    @Autowired
    private KafkaTemplate<Bytes, Bytes> kafkaTemplate;

    /**
     * 单个单个发送
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void sendData() {

        if (index++ % 2 == 0) {
            XqListenableFutureCallback xqListenableFutureCallback = new XqListenableFutureCallback();

            String dataA = "TopicA=data";
            String dataB = "TopicB=data";

            ListenableFuture<SendResult<Bytes, Bytes>> future = kafkaTemplate.send(topicA, Bytes.wrap(dataA.getBytes()));
            future.addCallback(xqListenableFutureCallback);

            kafkaTemplate.send(topicB, Bytes.wrap(dataB.getBytes()));
            kafkaTemplate.flush();
        } else {
            String dataC = "TopicC=data";
            String dataD = "TopicD=data";
            kafkaTemplate.send(topicC, Bytes.wrap(dataC.getBytes()));
            kafkaTemplate.send(topicD, Bytes.wrap(dataD.getBytes()));
        }
    }

    class XqListenableFutureCallback implements ListenableFutureCallback<SendResult<Bytes, Bytes>> {
        @Override
        public void onFailure(Throwable ex) {
//            LOGGER.warn("发送失败", ex);
        }

        @Override
        public void onSuccess(SendResult<Bytes, Bytes> result) {
//            LOGGER.warn("发送成功. partitions:{}, offset:{} timestamp:{}",
//                    result.getRecordMetadata().partition(), result.getRecordMetadata().offset(), result.getRecordMetadata().timestamp());
        }
    }
}
