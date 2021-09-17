package com.study.base.任务管理_线程池;

import com.study.base.任务管理_线程池.task.RunnableTask;
import com.study.base.任务管理_线程池.task.ThreadTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可调试任务池-可设定任务初次执行和周期执行的时间间隔
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new RunnableTask("Name-RunnableTask"), 5, 30, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new ThreadTask("Name-ThreadTask"), 5, 30, TimeUnit.SECONDS);

//        executors.shutdown();
//        executors.wait();
        System.out.println("main end;");
    }
}
