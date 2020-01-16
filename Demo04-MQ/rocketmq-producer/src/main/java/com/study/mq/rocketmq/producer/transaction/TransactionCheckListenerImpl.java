package com.study.mq.rocketmq.producer.transaction;

import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;


/**
 * 未决事务，服务器回查客户端，broker端发起请求代码没有被调用，所以此处代码可能没用。
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);


    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("TransactionCheckListenerImpl 检查 TrMsg " + msg.toString());

        int value = transactionIndex.getAndIncrement();
        if ((value % 6) == 0) {
            System.out.println("TransactionCheckListenerImpl 111");
            throw new RuntimeException("Could not find db");
        }
        else if ((value % 5) == 0) {
            System.out.println("TransactionCheckListenerImpl ROLLBACK_MESSAGE");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        else if ((value % 4) == 0) {
            System.out.println("TransactionCheckListenerImpl COMMIT_MESSAGE");
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        System.out.println("TransactionCheckListenerImpl UNKNOW");
        return LocalTransactionState.UNKNOW;
    }
}