package com.study.base.threadpool;

import com.study.base.threadpool.task.RunnableTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author sk-qianxiao
 * @date 2020/1/13
 */
public class SingleThreadScheduledExecutorTest {
    public static void main(String[] args) throws Exception{
        ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();
        executors.scheduleAtFixedRate(new RunnableTask("ReflectTest"), 5, 30, TimeUnit.SECONDS);

//        executors.shutdown();
//        executors.wait();
        System.out.println("main end;");
    }
}
