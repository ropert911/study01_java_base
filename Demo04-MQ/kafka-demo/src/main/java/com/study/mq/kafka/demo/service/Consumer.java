package com.study.mq.kafka.demo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    //单个-自动提交
    @KafkaListener(group = "${spring.kafka.consumer.group-id}", //分组
            topics = "${kafka.test.topic.testA}",               //topic
            containerFactory = "kafkaListenerSingleAutoACK")
    void sigalAutoCommit(ConsumerRecord<String, Bytes> data) {
        LOGGER.warn("单个-自动ACK topic={} partition={} offset={} timestamp={} key={} value={}",
                data.topic(), data.partition(), data.offset(), data.timestamp(), data.key(), new String(data.value().get()));
    }

    /**
     * 单个-手动提交
     *
     * @param data
     * @param ack
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "${kafka.test.topic.testB}",
            containerFactory = "kafkaListenerSingleManualACK")
    void sigalManualCommit(ConsumerRecord<String, Bytes> data, Acknowledgment ack) {
        LOGGER.warn("单个-手动ACK === topic={} partition={} offset={} timestamp={} key={} value={}",
                data.topic(), data.partition(), data.offset(), data.timestamp(), data.key(), new String(data.value().get()));
        ack.acknowledge();
    }

    /**
     * 批量-自动提交
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "${kafka.test.topic.testC}",
            containerFactory = "kafkaListenerBatchAutoACK")
    public void batchAutoCommit(List<Bytes> list) {
        list.parallelStream().forEach(record -> {
            String msg = new String(record.get());
            LOGGER.warn("批量-自动ACK === msg={}", msg);
        });
    }

    /**
     * 批量-手动提交
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "${kafka.test.topic.testD}",
            containerFactory = "kafkaListenerBatchManualACK")
    public void batchManalCommit(List<Bytes> list, Acknowledgment ack) {
        list.parallelStream().forEach(record -> {
            String msg = new String(record.get());
            LOGGER.warn("批量-手动ACK === msg={}", msg);
        });

        ack.acknowledge();
    }
}
