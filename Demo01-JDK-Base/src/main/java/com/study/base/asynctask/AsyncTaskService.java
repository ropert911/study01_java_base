package com.study.base.asynctask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 可见性设定 分为程序单个 session, request  调用一次生成一次
 *
 * @author sk-qianxiao
 */

@Service
public class AsyncTaskService {
    @Async
    public void excuteAsyncTask(Integer i) {
        System.out.println("异步执行任务【" + i + "】的线程threadid=[" + Thread.currentThread().getId());
    }
}
