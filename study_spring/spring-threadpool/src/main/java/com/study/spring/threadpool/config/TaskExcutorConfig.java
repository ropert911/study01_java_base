package com.study.spring.threadpool.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by xq on 2017/11/18.
 */
@Configuration
@ComponentScan("com.study.spring.threadpool")
@EnableAsync  //开启异步任务支持
public class TaskExcutorConfig implements AsyncConfigurer{
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);        //线程数
        taskExecutor.setMaxPoolSize(10);        //最大线程个数
        taskExecutor.setQueueCapacity(25);      //后台队列可放的任务个数
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
