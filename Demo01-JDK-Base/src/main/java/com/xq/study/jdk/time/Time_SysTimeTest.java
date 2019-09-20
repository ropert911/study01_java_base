package com.xq.study.jdk.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xq on 2017/9/23.
 */
public class Time_SysTimeTest {
    private static Logger LOGGER = LoggerFactory.getLogger(Time_SysTimeTest.class);

    public static void main(String[] args) {
        long start = System.nanoTime();
        LOGGER.error("启动时System.nanoTime==>{}", start);

        LOGGER.error("当前毫秒数UTC==>{}", System.currentTimeMillis());

        LOGGER.error("总运行时间(毫秒)==>{}", (System.nanoTime() - start)/1_000_000);
    }
}
