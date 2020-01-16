package com.study.mq.rocketmq.transcustomer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TransactionApplication {

    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(TransactionApplication.class);

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group");
        consumer.setInstanceName("rmq-instance");
        consumer.setNamesrvAddr("192.168.40.80:9876");
        consumer.setVipChannelEnabled(false);

        //设置消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    LOGGER.error("收到消息=={}", new String(msg.getBody()));
                }
                ConsumeConcurrentlyStatus status = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                ConsumeConcurrentlyStatus status = ConsumeConcurrentlyStatus.RECONSUME_LATER;
                LOGGER.error("消息已使用，返回结果=={}", status);
                return status;
            }
        });
        try {
            consumer.subscribe("transaction-test", "TagA");
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("开始监听.");
    }
}
