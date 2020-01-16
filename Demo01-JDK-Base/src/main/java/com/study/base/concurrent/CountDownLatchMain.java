package com.study.base.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 */
public class CountDownLatchMain {
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchMain.class);

    private static final int COUNT = 10;

    public static void main(String[] args) throws Exception {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(COUNT);

        for (int i = 0; i < COUNT; i++) {
            new Thread(() -> {
                try {
                    logger.info("thired wait...");
                    startLatch.await();
                    logger.info("thired run *****");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    /**计数器减1*/
                    endLatch.countDown();
                }
            }).start();
        }

        sleep(1000);

        startLatch.countDown();
        endLatch.await();
    }
}