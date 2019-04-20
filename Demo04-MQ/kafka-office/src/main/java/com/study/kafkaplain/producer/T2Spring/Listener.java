package com.study.kafkaplain.producer.T2Spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sk-qianxiao on 2018/1/24.
 */
@Component
public class Listener {
    public static Logger logger = LoggerFactory.getLogger(Listener.class);

    @KafkaListener(topics = "test2_topic",               //topic
            containerFactory = "kafkaListenerContainerFactory") //监听类
    public void listen(List<String> list) throws Exception {
        logger.warn("============get size {} data:{}", list.size(), list);
    }
}
