package com.study.mq.kafka.spring.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sk-qianxiao
 * @date 2018/1/24.
 */
@Service
public class KafkaConsumer {
    public static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * topics,监听topic
     * containerFactory 监听类
     * 注意：低版本的group设置是无效的
     *
     * @param records
     * @throws Exception
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "${xq.kafka.topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen1(List<ConsumerRecord<String, String>> records,
                        Acknowledgment ack) {
        logger.warn("============get size {}", records.size());
        for (ConsumerRecord<String, String> record : records) {
            logger.info("  topic=={} key=={} value=={}", record.topic(), record.key(), record.value());
        }

        ack.acknowledge();
    }

    @KafkaListener(topics = "${xq.kafka.topic2}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen2(List<String> messages,
                        Acknowledgment ack,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic2) {
        logger.warn("============get size {} ", messages.size());
        for (String msg : messages) {
            logger.info("  topic=={} message=={}", topic2, msg);
        }
        ack.acknowledge();
    }
}
