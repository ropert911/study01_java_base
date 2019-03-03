package com.xq.study.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xq on 2017/9/23.
 */
public class 时间4_系统时间Test {
    private static Logger LOGGER = LoggerFactory.getLogger(时间4_系统时间Test.class);

    public static void main(String[] args) {
        long start = System.nanoTime();
        LOGGER.error("启动时System.nanoTime==>{}", start);

        LOGGER.error("当前毫秒数UTC==>{}", System.currentTimeMillis());

        LOGGER.error("总运行时间(毫秒)==>{}", (System.nanoTime() - start)/1_000_000);
    }
}
