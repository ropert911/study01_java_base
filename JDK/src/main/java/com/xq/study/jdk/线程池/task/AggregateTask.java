package com.xq.study.jdk.线程池.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sk-qianxiao on 2017/11/20.
 */
public class AggregateTask implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AggregateTask.class);

    private String command;

    public AggregateTask(String key) {
        this.command = key;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setName(thread.getName() + "-" + command);
        LOGGER.error("做任务 {}", command);
    }
}