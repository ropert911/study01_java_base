package com.study.mq.kafka.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

@Configuration
@EnableKafka
@ComponentScan
public class ConfigConsumer {

    @Value(value = "${spring.kafka.listener.concurrency:3}")
    private Integer concurrency;

    @Autowired
    private ConsumerFactory consumerFactory;

    /**
     * 单个自动
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerSingleAutoACK() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        /**每个listener在初始化的时候设置的并发消费者的个数*/
        factory.setConcurrency(concurrency);
        /**是否支持批量*/
        factory.setBatchListener(false);
//        /**设置ACK模式*/
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }

    /**
     * 单个手动
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerSingleManualACK() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        /**每个listener在初始化的时候设置的并发消费者的个数*/
        factory.setConcurrency(concurrency);
        /**是否支持批量*/
        factory.setBatchListener(false);
        /**设置ACK模式*/
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }

    /**
     * 批量自动
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerBatchAutoACK() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        /**每个listener在初始化的时候设置的并发消费者的个数*/
        factory.setConcurrency(concurrency);
        /**是否支持批量*/
        factory.setBatchListener(true);
//        /**设置ACK模式*/
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }

    /**
     * 批量手动
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerBatchManualACK() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        /**每个listener在初始化的时候设置的并发消费者的个数*/
        factory.setConcurrency(concurrency);
        /**是否支持批量*/
        factory.setBatchListener(true);
        /**设置ACK模式*/
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
        return factory;
    }
}
