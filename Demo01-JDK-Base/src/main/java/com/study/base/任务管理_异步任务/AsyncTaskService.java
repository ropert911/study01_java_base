package com.study.base.任务管理_异步任务;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncTaskService {
    @Async
    public Future<String> excuteAsyncTask(Integer i) {
        System.out.println("异步执行任务【" + i + "】的线程threadid=[" + Thread.currentThread().getId());

        return new AsyncResult<>("任务" + i + "完成");
    }
}
