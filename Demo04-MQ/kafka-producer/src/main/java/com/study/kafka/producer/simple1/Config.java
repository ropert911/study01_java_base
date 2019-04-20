package com.study.kafka.producer.simple1;

import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by sk-ziconglu on 2017/3/9.
 */
@Configuration
@EnableKafka
public class Config {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private KafkaTemplate<Bytes, Bytes> kafkaTemplate;

    private String topic = "kafka.test.topic.testA";

    @PostConstruct
    public void init() throws Exception {
    }

    public KafkaTemplate<Bytes, Bytes> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public String getTopic() {
        return topic;
    }
}
