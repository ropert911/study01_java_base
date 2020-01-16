package com.study.mq.rocketmq.producer.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送事务消息例子
 */
public class DemoTransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        Logger LOGGER = LoggerFactory.getLogger(DemoTransactionProducer.class);


        TransactionMQProducer producer = new TransactionMQProducer("rmq-group");
        producer.setInstanceName("rmq-instance");
        producer.setNamesrvAddr("192.168.40.80:9876");
        producer.setVipChannelEnabled(false);       //不去访问10909

        producer.setCheckThreadPoolMinSize(2);      // 事务回查最小并发数
        producer.setCheckThreadPoolMaxSize(2);      // 事务回查最大并发数
        producer.setCheckRequestHoldMax(2000);      // 队列数

        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        producer.setTransactionCheckListener(transactionCheckListener);

        try {
            producer.start();

            Message msg = new Message("transaction-test", "TagA", "KEYA",
                    ("RocketMQ 事务消息").getBytes());

            TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
            LOGGER.error("发送消息 开始");
            SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, null);
            LOGGER.error("发送消息 结果=={}", sendResult);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        Thread.sleep(10);
        producer.shutdown();
    }
}