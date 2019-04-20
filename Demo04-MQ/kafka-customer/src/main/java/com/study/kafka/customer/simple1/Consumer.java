package com.study.kafka.customer.simple1;

import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
//    //单个-自动提交
//    @KafkaListener(group = "${spring.kafka.consumer.group-id}", //分组
//            topics = "${kafka.test.topic.testA}",               //topic
//            containerFactory = "kafkaListenerContainerFactory")
//    void sigalAutoCommit(ConsumerRecord<String, Bytes> data) {
//        LOGGER.warn("topic={} partition={} offset={} timestamp={} key={} value={}",
//                data.topic(), data.partition(), data.offset(), data.timestamp(), data.key(), new String(data.value().get()));
//    }

//单个-手动提交
//    @KafkaListener(group = "${spring.kafka.consumer.group-id}", //分组
//            topics = "${kafka.test.topic.testA}",               //topic
//            containerFactory = "kafkaListenerContainerFactory")
//    void sigalManualCommit(ConsumerRecord<String, Bytes> data, Acknowledgment ack) {
//        LOGGER.warn("topic={} partition={} offset={} timestamp={} key={} value={}",
//                data.topic(), data.partition(), data.offset(), data.timestamp(), data.key(), new String(data.value().get()));
//        ack.acknowledge();
//    }

//    //批量-自动提交
//    @KafkaListener(group = "${spring.kafka.consumer.group-id}", //分组
//            topics = "${kafka.test.topic.testA}",               //topic
//            containerFactory = "kafkaListenerContainerFactory") //监听类
//    public void batchManalCommit(List<Bytes> list) {
//        LOGGER.warn("thread {} receive {} 消息", Thread.currentThread().getId(), list.size());
//
//        list.parallelStream().forEach(record -> {
//            String msg = new String(record.get());
//            LOGGER.warn("thread {} handle msg={}", Thread.currentThread().getId(), msg);
//        });
//
//        LOGGER.warn("thread onAPMessage end ");
//    }

    //批量-手动提交
    @KafkaListener(group = "${spring.kafka.consumer.group-id}", //分组
            topics = "${kafka.test.topic.testA}",               //topic
            containerFactory = "kafkaListenerContainerFactory") //监听类
    public void batchManalCommit(List<Bytes> list, Acknowledgment ack) {
        LOGGER.warn("thread {} receive {} 消息", Thread.currentThread().getId(), list.size());

        list.parallelStream().forEach(record -> {
            String msg = new String(record.get());
            LOGGER.warn("thread {} handle msg={}", Thread.currentThread().getId(), msg);
        });

        ack.acknowledge();
        LOGGER.warn("thread onAPMessage end ");
    }
}
