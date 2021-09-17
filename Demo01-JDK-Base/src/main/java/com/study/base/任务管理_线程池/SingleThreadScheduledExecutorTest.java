package com.study.base.任务管理_线程池;

import com.study.base.任务管理_线程池.task.RunnableTask;
import com.study.base.任务管理_线程池.task.ThreadTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 单线程的可调试任务池-可设定任务初次执行和周期执行的时间间隔
 */
public class SingleThreadScheduledExecutorTest {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.scheduleAtFixedRate(new RunnableTask("Name-RunnableTask"), 5, 10, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleAtFixedRate(new ThreadTask("Name-ThreadTask"), 5, 10, TimeUnit.SECONDS);

//        executors.shutdown();
//        executors.wait();
        System.out.println("main end;");
    }
}
