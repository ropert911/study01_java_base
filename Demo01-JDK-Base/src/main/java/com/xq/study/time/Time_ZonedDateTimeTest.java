package com.xq.study.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by xq on 2017/9/23.
 */
public class Time_ZonedDateTimeTest {
    private static Logger LOGGER = LoggerFactory.getLogger(Time_ZonedDateTimeTest.class);
    public static void main(String[] args){
        zonedDateTimeTest();
        printZimeZone();
    }
    /**
     * 时区时间测试
     */
    public static void zonedDateTimeTest() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        System.out.println(romeZone);
        ZoneId romeZone2 = TimeZone.getDefault().toZoneId();    //Asia/Shanghai
        System.out.println(romeZone2);

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        Instant instant = Instant.now();
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);   //时间进行时区转换
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);      //时间进行时区转换
        ZonedDateTime zdt3 = instant.atZone(romeZone);         //时间进行时区转换
        System.out.println("LocalDate     转 ZonedDateTime :" + zdt1);
        System.out.println("LocalDateTime 转 ZonedDateTime :" + zdt2);
        System.out.println("Instant       转 ZonedDateTime :" + zdt3);
        System.out.println("ZonedDateTime 转 LocalDate     :" + zdt1.toLocalDate());
        System.out.println("ZonedDateTime 转 LocalTime     :" + zdt1.toLocalTime());
        System.out.println("ZonedDateTime 转 LocalDateTime :" + zdt1.toLocalDateTime());
        System.out.println("ZonedDateTime 转 Instant       :" + zdt1.toInstant());

        System.out.println("LocalDate + LocalTime  转 LocalDateTime :" + LocalDateTime.of(date, LocalTime.of(1, 2, 3)));
        System.out.println("Instant       转 LocalDateTime :" + LocalDateTime.ofInstant(instant, romeZone));
        System.out.println("LocalDateTime 转 Instant       :" + dateTime.toInstant(ZoneOffset.of("-08:00")));
        System.out.println("LocalDateTime 转 LocalDate     :" + dateTime.toLocalDate());
        System.out.println("LocalDateTime 转 LocalTime     :" + dateTime.toLocalTime());
    }

    /**
     * 打印时区信息
     */
    public static void printZimeZone() {
        TimeZone tz = TimeZone.getDefault();
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
    }
}
