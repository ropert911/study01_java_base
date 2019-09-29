package com.study.spring.threadpool.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by xq on 2017/11/18.
 */

//可见性设定 分为程序单个 session, request  调用一次生成一次
//@Scope("prototype")
@Service
public class AsyncTaskService {
    @Async
    public void excuteAsyncTask(Integer i) {
        System.out.println("threadid=[" + Thread.currentThread().getId() + "]异步执行任务11--:" + i);
    }

    @Async
    public void excuteAsyncTask2(Integer i) {
        System.out.println("threadid=[" + Thread.currentThread().getId() + "]异步执行任务22--:" + i);
    }
}
