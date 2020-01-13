package com.xq.study.threadpool.task;

import java.util.concurrent.Callable;

/**
 * Created by wangxc on 2017/3/29.
 */
public class CallableTask implements Callable<Integer> {

    private int num;

    public CallableTask(int num) {
        this.num = num;
    }

    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "CallableTask任务【" + num + "】--->执行中");
        Thread.sleep(10);
        return num;
    }
}
