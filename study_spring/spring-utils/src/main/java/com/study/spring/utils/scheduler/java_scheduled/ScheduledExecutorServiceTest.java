package com.study.spring.utils.scheduler.java_scheduled;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xq on 2017/10/19.
 */
public class ScheduledExecutorServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(ScheduledExecutorServiceTest.class);

    private static void test1() {
        LOGGER.error("创建固定周期的");

        Runnable runnable = new Runnable() {

            //run里头的是执行主体
            @Override
            public void run() {
                LOGGER.error("博客虫讲解的storm视频，马上会在极客学院上线，欢迎关注！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

//        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService service1 = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("xq-thread-pool-%d")
                        .daemon(false) //true 后台选择，不阻塞schedule调用  false要阻塞
                        .build());
        // 设置时间间隔，如示例,1为延迟时间，3为周期,最后一个为时间单位
        //看起来和scheduleAtFixedRate一样，都是固定周期
        service1.scheduleAtFixedRate(runnable, 1, 3, TimeUnit.SECONDS);
        LOGGER.error("任务完成");
    }

    public static void main(String[] args) {
        test1();
    }
}
