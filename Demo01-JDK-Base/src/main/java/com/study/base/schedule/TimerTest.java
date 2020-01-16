package com.study.base.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author sk-qianxiao
 */

public class TimerTest {
    private static Logger logger = LoggerFactory.getLogger(TimerTest.class);

    public static void test() {
        logger.error("创建 固定周期");
        Timer timer = new Timer();

        //从效果看 和 scheduleAtFixedRate 一样，都是固定周期，不是时间间隔
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                try {
                    logger.error("任务。。。开始");
                    Thread.sleep(2000);
                    logger.error("任务。。。结束 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2000, 3000);
        logger.info("end of test");
    }

    public static void main(String[] args) {
        test();
    }
}
