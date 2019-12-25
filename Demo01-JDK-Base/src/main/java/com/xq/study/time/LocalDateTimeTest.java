package com.xq.study.time;

import java.time.*;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Created by xq on 2017/9/23.
 */
public class LocalDateTimeTest {
    public static void main(String[] args){
        localDateTimeTest();
    }

    /**
     * LocalDateTime 日期+time
     */
    public static void localDateTimeTest() {
        //构建方式1
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1);
        //构建方式二
        LocalDate date = LocalDate.parse("2014-03-18");
        LocalTime time = LocalTime.parse("13:45:20");
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        System.out.println(dt2);
        //构建方式3-1 日期设备时间
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        System.out.println(dt3);
        //构建方式3-2   日期+time
        LocalDateTime dt4 = date.atTime(time);
        System.out.println(dt4);
        //构建方式4
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt5);

        //获取值
        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);  //2014-03-18
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);  //13:45:20

        //设置值：通过直接设备和加减相对时间两种
        dt1 = dt1.withYear(2015);
        dt1 = dt1.withHour(13);
        System.out.println("设置日期-时间1：" + dt1);
        dt1 = dt1.minusYears(1);
        dt1 = dt1.minusHours(3);
        System.out.println("设置日期-时间2：" + dt1);
        //特殊设置，可以参考 LocalDate
        dt1.with(firstInMonth(DayOfWeek.MONDAY));
    }
}
