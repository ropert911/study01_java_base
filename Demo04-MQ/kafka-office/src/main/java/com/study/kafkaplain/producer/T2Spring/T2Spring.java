package com.study.kafkaplain.producer.T2Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by sk-qianxiao on 2018/1/24.
 */
@Component
public class T2Spring {
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void test() throws Exception {
        kafkaTemplate.send("test2_topic", "foo11115");
        kafkaTemplate.send("test2_topic", "foo11116");
        kafkaTemplate.flush();
        Thread.sleep(1000);
    }
}
