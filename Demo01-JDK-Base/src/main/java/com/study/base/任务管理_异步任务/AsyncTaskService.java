package com.study.base.任务管理_异步任务;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {
    @Async
    public void excuteAsyncTask(Integer i) {
        System.out.println("异步执行任务【" + i + "】的线程threadid=[" + Thread.currentThread().getId());
    }
}
