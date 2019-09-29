package com.xq.study.jdk.time;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by xq on 2017/9/23.
 */
public class LocalDateTest {
    /**
     * LocalDate 日期
     */
    @Test
    public void localDateTest() {
        LocalDate date = LocalDate.parse("2014-03-18");     //2014-03-18
        date = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        date = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        date = LocalDate.of(2014, 3, 18);       //2014-03-18

        //获取值
        System.out.println(date);                           //2014-03-18
        System.out.println(" Year:" + date.getYear());     //2014
        System.out.println(" isLeapYear:" + date.isLeapYear());        //false    是否闰年
        System.out.println(" Month:" + date.getMonth());           //MARCH
        System.out.println(" lengthOfMonth:" + date.lengthOfMonth());      //31 本月有多少天
        System.out.println(" MonthValue" + date.getMonthValue());  //3
        System.out.println(" Day:" + date.getDayOfMonth());         //18
        System.out.println(" dayOfWeek:" + date.getDayOfWeek());           //TUESDAY
        //另一种方式获取值
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(year + " " + month + " " + day);     //2014 3 18

        //格式化输出
        LocalDate datef = LocalDate.of(2014, 3, 18);
        String s1 = datef.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化日期输出1：" + s1); //20140318
        String s2 = datef.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("格式化日期输出2：" + s2); //2014-03-18
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String s3 = datef.format(formatter);
        System.out.println("格式化日期输出3：" + s3);

        //设置值：直截指定方式
        LocalDate date1 = LocalDate.of(2014, 3, 18);        //2014-03-18
        date1 = date1.withYear(2010);
        date1 = date1.withMonth(11);
        date1 = date1.with(ChronoField.MONTH_OF_YEAR, 9);
        date1 = date1.withDayOfMonth(12);
        System.out.println("设置后的日期1：" + date1);
        //设置值：以相对方式
        date1 = LocalDate.of(2014, 3, 18);
        date1 = date1.minusYears(3);
        date1 = date1.plusWeeks(1);
        date1 = date1.plus(6, ChronoUnit.MONTHS);
        System.out.println("设置后的日期2：" + date1);
        //特定设置
        date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY)); //下一个周日，如果今天是，就返回今天,类型的previousOrSame
        date1.with(firstInMonth(DayOfWeek.MONDAY));     //创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
        date1.with(lastInMonth(DayOfWeek.MONDAY));  //创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
        LocalDate date3 = date2.with(lastDayOfMonth()); //类似：firstDayOfYear / lastDayOfYear, firstDayOfNextYear / lastDayOfNextYear  firstDayOfMonth / lastDayOfMonth, firstDayOfNextMonth / lastDayOfNextMonth
    }
}
