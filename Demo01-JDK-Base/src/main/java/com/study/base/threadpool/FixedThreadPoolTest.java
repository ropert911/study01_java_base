package com.study.base.threadpool;

import com.study.base.threadpool.task.ThreadTask;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author sk-qianxiao
 * @date 2020/1/13
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) throws Exception{
        Executor taskExecutor = Executors.newFixedThreadPool(2);

        taskExecutor.execute(new ThreadTask("task1"));

        System.out.println("main end;");
    }
}
