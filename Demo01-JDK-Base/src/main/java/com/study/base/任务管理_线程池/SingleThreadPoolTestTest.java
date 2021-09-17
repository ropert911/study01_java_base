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
 * 只一个线程的线程池
 * 问题：允许请求队列长度为Integer.MAX_VALUE
 */
public class SingleThreadPoolTestTest {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newSingleThreadExecutor();
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


