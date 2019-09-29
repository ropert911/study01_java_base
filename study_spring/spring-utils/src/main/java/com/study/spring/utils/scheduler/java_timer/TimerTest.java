package com.study.spring.utils.scheduler.java_timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xq on 2017/10/19.
 */

public class TimerTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TimerTest.class);

    public static void test() {
        LOGGER.error("创建 固定周期");
        Timer timer = new Timer();

        //从效果看 和 scheduleAtFixedRate 一样，都是固定周期，不是时间间隔
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.error("触发");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000, 3000);
    }

    public static void main(String[] args) {
        test();
    }
}
