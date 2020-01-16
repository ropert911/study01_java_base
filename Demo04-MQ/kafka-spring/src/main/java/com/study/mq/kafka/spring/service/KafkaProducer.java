package com.study.mq.kafka.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * @author xq
 * Created by sk-qianxiao on 2018/1/24.
 */
@Service
public class KafkaProducer {
    @Value("${xq.kafka.topic}")
    private String topic;

    @Value("${xq.kafka.topic2}")
    private String topic2;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron = "*/3 * * * * ?")
    public void test1() {
        kafkaTemplate.send(topic, "data-only");
        kafkaTemplate.send(topic, "abc", "data with key");
        kafkaTemplate.flush();
    }

    @Scheduled(cron = "*/3 * * * * ?")
    public void test2() {
        kafkaTemplate.send(topic2, "data-only");
        kafkaTemplate.send(topic2, "abc", "data with key");
        kafkaTemplate.flush();
    }
}
