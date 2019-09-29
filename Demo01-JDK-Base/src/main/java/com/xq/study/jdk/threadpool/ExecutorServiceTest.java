package com.xq.study.jdk.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xq.study.jdk.threadpool.task.AggregateTask;
import com.xq.study.jdk.threadpool.task.MyThread;
import com.xq.study.jdk.threadpool.task.TaskSleep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangxc on 2017/3/27.
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
//        ExecutorService es = Executors.newFixedThreadPool(10);    //指标线程数量

        useThreadRunable(); //一个一个加任务:添加 Runalbe
        useThread1();   //一个一个加任务:添加 Thread任务
        useThread2();  //批量添加要执行的任务

        useThread3();      //手动建 ExecutorService,  :添加 Runaal任务

    }

    private static void useThreadRunable() {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            Runnable task = new AggregateTask("test");
            exec.submit(task);
        } catch (Exception e) {

        }
    }

    private static void useThread1() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            exec.execute(new MyThread("张" + i));
        }
        exec.shutdown();//并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
    }

    private static void useThread2() {
        long stat = System.currentTimeMillis();

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<TaskSleep> callList = new ArrayList<TaskSleep>();
        for (int i = 0; i < 10000; i++) {
            callList.add(new TaskSleep(i));
        }

        try {
            List<Future<Integer>> futures = executorService.invokeAll(callList);
            executorService.shutdown();

            int sum = 0;
            for (Future<Integer> item : futures) {
                sum += item.get();
            }
            System.out.println("结果" + sum);
        } catch (Exception e) {

        }

        long end = System.currentTimeMillis();
        System.out.println((double) (end - stat) / 1000);
    }

    public static void useThread3() {
        BlockingQueue<Runnable> QUEUE = new SynchronousQueue<>();
        ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("AggregateService-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(5,
                80,
                200L,
                TimeUnit.MILLISECONDS,
                QUEUE,
                THREAD_FACTORY);

        try {
            for (int i = 0; i < 15; i++) {
                Runnable task = new AggregateTask("Test" + i);
                executorService.submit(task);
            }

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ExecutorService scheduledExecutorService() {
        ScheduledExecutorService executors = Executors.newScheduledThreadPool(1);
        executors.scheduleAtFixedRate(() -> {
            //TODO:这里做任务
        }, 5, 30, TimeUnit.SECONDS);
        return executors;
    }
}


