package com.xq.study.threadpool;

import com.xq.study.threadpool.task.RunnableTask;
import com.xq.study.threadpool.task.ThreadTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;

/**
 * @author sk-qianxiao
 * @date 2020/1/13
 */
public class ThreadPoolTaskExecutorTest {
    public static void main(String[] args) throws Exception{

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        /**线程数*/
        taskExecutor.setCorePoolSize(5);
        /**最大线程个数*/
        taskExecutor.setMaxPoolSize(10);
        /**后台队列可放的任务个数*/
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();

        taskExecutor.submit(new RunnableTask("task1"));
        taskExecutor.execute(new ThreadTask("task2"));

        System.out.println("main end;");
    }
}
