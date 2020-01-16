package com.study.base.threadpool;

import com.study.base.threadpool.task.CallableTask;
import com.study.base.threadpool.task.RunnableTask;
import com.study.base.threadpool.task.ThreadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author sk-qianxiao
 */
public class CachedThreadPoolTest {
    public static void main(String[] args){
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.submit(new RunnableTask("test"));
            exec.execute(new ThreadTask("张"));


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


