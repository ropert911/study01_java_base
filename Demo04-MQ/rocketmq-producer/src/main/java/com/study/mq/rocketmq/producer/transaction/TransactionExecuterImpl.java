package com.study.mq.rocketmq.producer.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行本地事务
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    Logger LOGGER = LoggerFactory.getLogger(TransactionExecuterImpl.class);

    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        int value = 2;
        if (value == 0) {
            System.out.println("执行失败，value==0");
            throw new RuntimeException("Could not find db");
        } else if (value == 1) {
            LOGGER.error("执行失败，需要回滚");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if (value == 2) {
            LOGGER.error("执行完成");
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        LOGGER.error("执行未知错误");
        return LocalTransactionState.UNKNOW;
    }
}