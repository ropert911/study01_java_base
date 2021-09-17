package com.study.base.任务管理_线程池;

import com.study.base.任务管理_线程池.task.RunnableTask;
import com.study.base.任务管理_线程池.task.ThreadTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 手动建
 *
 * @author sk-qianxiao
 * @date 2020/1/13
 */
public class ThreadPoolTaskExecutorTest {
    public static void main(String[] args) throws Exception {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        /**线程数*/
        taskExecutor.setCorePoolSize(5);
        /**最大线程个数*/
        taskExecutor.setMaxPoolSize(10);
        /**后台队列可放的任务个数*/
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();

        taskExecutor.submit(new RunnableTask("Name-RunnableTask"));
        taskExecutor.execute(new ThreadTask("Name-ThreadTask"));

        System.out.println("main end;");
    }
}
