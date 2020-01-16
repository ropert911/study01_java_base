package com.study.base.kafka.spring;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;



public class Example1Test {
    public static Logger logger = LoggerFactory.getLogger(Example1Test.class);
    public static String topic1 = "xqtest1_topic1";
    public static String topic2 = "xqtest1_topic2";

    public static void main(String[] args) {
        ExampleConfig kafkaSpringTest = new ExampleConfig();

        ContainerProperties containerProps = new ContainerProperties(topic1, topic2);
        containerProps.setMessageListener(new MessageListener<String, String>() {
            @Override
            public void onMessage(ConsumerRecord<String, String> record) {
                logger.info("==================== topic=={} offset={} key=={} value=={}", record.topic(), record.offset(), record.key(), record.value());
            }
        });
        KafkaMessageListenerContainer<Integer, String> container = kafkaSpringTest.createContainer(containerProps);
        container.setBeanName("testAuto");
        /**开始监听*/
        container.start();

        /**发数据*/
        KafkaTemplate<String, String> template = kafkaSpringTest.createTemplate();
        testSend(template);

        try {
            Thread.sleep(1000); // wait a bit for the container to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        container.stop();
    }

    private static void testSend(KafkaTemplate<String, String> template) {
        template.setDefaultTopic(topic1);
        template.sendDefault("0", "t1");
        template.sendDefault("2", "t2");
        template.sendDefault("0", "t3");
        template.sendDefault("2", "t4");
        template.setDefaultTopic(topic2);
        template.sendDefault("0", "t5");
        template.sendDefault("2", "t6");
        template.sendDefault("0", "t7");
        template.sendDefault("2", "t8");
        template.flush();
    }
}
