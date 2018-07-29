package com.xq.study.jdk.timetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by sk-qianxiao on 2017/11/9.
 */
public class TimeZoneTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TimeZoneTest.class);

    public static void printTZ(TimeZone tz) {
        LOGGER.info("========================");
        LOGGER.info("详情 {}", tz);
        LOGGER.info("显示名称 {}", tz.getDisplayName());
        LOGGER.info("显示名称-缩写 {}", tz.getDisplayName(false, 0));
        LOGGER.info("显示名称-夏时令-缩写 {}", tz.getDisplayName(true, 0));
        LOGGER.info("显示名称-全 {}", tz.getDisplayName(false, 1));
        LOGGER.info("显示名称-夏时令-全 {}", tz.getDisplayName(true, 1));
        LOGGER.info("英文显示名称 {}", tz.getDisplayName(Locale.ENGLISH));
        LOGGER.info("地区 {}", tz.getID());
        LOGGER.info("时间偏移 {}", tz.getRawOffset());
        LOGGER.info("这个时区在1988年5月12日夏时令下的偏移 {}", tz.getOffset(579414630000L));
        LOGGER.info("这个时区在1988年5月12日23：59：59秒夏时令下的偏移 {}",
                tz.getOffset(1, 1988, 5, 12, 2, 86399000));
        LOGGER.info("========================");
    }

    public static void main(String[] args) {
        TimeZone tz = TimeZone.getDefault();
        printTZ(tz);
    }
}
