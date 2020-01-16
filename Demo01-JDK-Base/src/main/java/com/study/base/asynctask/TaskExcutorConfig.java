package com.study.base.asynctask;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 开启异步任务支持
 *
 * @author sk-qianxiao
 */
@Configuration
@ComponentScan("com.xq.study.asynctask")
@EnableAsync
public class TaskExcutorConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        /**线程数*/
        taskExecutor.setCorePoolSize(5);
        /**最大线程个数*/
        taskExecutor.setMaxPoolSize(10);
        /**后台队列可放的任务个数*/
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
