package com.xq.study.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author sk-qianxiao
 * @date 2020/1/15
 */
public class MyCallable  implements Callable<String> {
    private int count = 20;

    @Override
    public String call() throws Exception {
        for (int i = count; i > 0; i--) {
            System.out.println(Thread.currentThread().getName()+" [Callable线程]当前票数：" + i);
        }
        return "sale out";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread mThread = new Thread(futureTask);
        Thread mThread2 = new Thread(futureTask);
        Thread mThread3 = new Thread(futureTask);

        mThread.start();
        mThread2.start();
        mThread3.start();
        System.out.println(futureTask.get());

    }
}
