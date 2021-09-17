package com.study.base.任务管理_线程池;

import com.study.base.任务管理_线程池.task.CallableTask;
import com.study.base.任务管理_线程池.task.RunnableTask;
import com.study.base.任务管理_线程池.task.ThreadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
 * 问题：线程数量最大可为Integer.MAX_VALUE，允许请求队列长度为Integer.MAX_VALUE
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.submit(new RunnableTask("Name-RunnableTask"));
            exec.execute(new ThreadTask("Name-ThreadTask"));


            List<CallableTask> callList = new ArrayList<CallableTask>();
            callList.add(new CallableTask(1));
            List<Future<Integer>> futures = exec.invokeAll(callList);

            //并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
            exec.shutdown();

            int sum = 0;
            for (Future<Integer> item : futures) {
                sum += item.get();
            }
            System.out.println("结果" + sum);
        } catch (Exception e) {

        }
    }
}


