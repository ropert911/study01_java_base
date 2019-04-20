package com.study.rocketmq.producer;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProduceApplication {

    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(ProduceApplication.class);

        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setInstanceName("rmq-instance");
        producer.setNamesrvAddr("192.168.40.80:9876");
        producer.setVipChannelEnabled(false);           //不去访问10909

        try {
            producer.start();

            for (int i = 0; i < 3; i++) {
                Message msg = new Message("TopicA-test", "TagA", ("ProduceApplication的消息" + i).getBytes()
                );

                LOGGER.error("开始发送消息=={}", msg);
                SendResult sendResult = producer.send(msg);
                LOGGER.error("消息发送结果=={}", sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
