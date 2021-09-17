package com.study.base.任务管理_线程池.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sk-qianxiao on 2017/11/20.
 */
public class RunnableTask implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunnableTask.class);

    private String command;

    public RunnableTask(String key) {
        this.command = key;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        thread.setName(thread.getName() + "-" + command);
        System.out.println("做任务 " + command);
    }
}