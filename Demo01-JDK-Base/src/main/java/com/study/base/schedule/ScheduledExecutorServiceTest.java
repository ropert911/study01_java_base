package com.study.base.schedule;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sk-qianxiao
 */
public class ScheduledExecutorServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(ScheduledExecutorServiceTest.class);

    private static void test1() {
        LOGGER.error("创建固定周期的");

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                LOGGER.error("通知任务1： 博客虫讲解的storm视频，马上会在极客学院上线，欢迎关注！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                LOGGER.error("通知任务2： 这是另一个任务！2222222");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                LOGGER.error("通知任务3： 由别的调整器执行！33333333333333333");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2,
                new BasicThreadFactory.Builder().namingPattern("xq-thread-pool-%d")
                        .daemon(false)      //true 后台选择，不阻塞schedule调用
                        .build());
        // 设置时间间隔，如示例,1为延迟时间，3为周期,最后一个为时间单位
        //看起来和scheduleAtFixedRate一样，都是固定周期
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable1, 1, 3, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable2, 1, 6, TimeUnit.SECONDS);

        //只有一个线程的执行器
        ScheduledExecutorService scheduledThreadPoolExecutor2 = Executors.newSingleThreadScheduledExecutor();
        scheduledThreadPoolExecutor2.scheduleAtFixedRate(runnable3, 1, 12, TimeUnit.SECONDS);

        LOGGER.error("任务创建完成");
    }

    public static void main(String[] args) {
        test1();
    }
}
