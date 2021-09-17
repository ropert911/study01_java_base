package com.study.base.任务管理_线程池;

import com.study.base.任务管理_线程池.task.RunnableTask;
import com.study.base.任务管理_线程池.task.ThreadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * 定长线程池的大小最好根据系统资源进行设置
 * 问题：允许请求队列长度为Integer.MAX_VALUE
 *
 * @author sk-qianxiao
 * @date 2020/1/13
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new RunnableTask("Name-RunnableTask"));
        executorService.execute(new ThreadTask("Name-ThreadTask"));

        System.out.println("main end;");
    }
}
