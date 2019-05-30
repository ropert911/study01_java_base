package com.xq.study.jdk.时间;

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
public class 时间1_日期时间Test {
    private static Logger LOGGER = LoggerFactory.getLogger(时间1_日期时间Test.class);

    public static void main(String[] args) {
        System.out.println("==============LocalDate 日期");
        localDateTest();

        System.out.println("==============LocalTime 时间");
        localTimeTest();

        System.out.println("==============LocalDateTime 日期+时间");
        localDateTimeTest();

        System.out.println("==============机器时间Instan");
        instantTest();

        System.out.println("==============Duration时间 Period日期 差");
        durationTest();
    }

    public static void localDateTest() {
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

    public static void localTimeTest() {
        LocalTime time = LocalTime.parse("13:45:20");
        time = LocalTime.of(13, 45, 20);  //13:45:20
        System.out.println(time);   //13:45:20

        //获取值
        int hour = time.getHour();      //13
        int minute = time.getMinute();  //45
        int second = time.getSecond();  //20
        System.out.println(hour + ":" + minute + ":" + second);

        //设置值：直接设置
        time = time.withHour(11);
        time = time.withMinute(13);
        time = time.withSecond(14);
        System.out.println("设置后的时间1：" + time);
        //设置值：加减
        time = time.plusHours(3);
        System.out.println("设置后的时间2：" + time);
    }

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
        //构建方式3-2   日期+时间
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

    public static void instantTest() {
        //下面几个都相同
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2,1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));

        System.out.println(Instant.now());
    }

    public static void durationTest() {
        //Duration:LocalDate
        Period p1 = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2015, 2, 19));
        System.out.println("日期相差1 Period：" + p1);  //P11M11D
        Period p2 = Period.ofDays(10);
        System.out.println("日期相差2 Period：" + p2);

        //Duration:localTime
        Duration d1 = Duration.between(LocalTime.parse("13:45:20"), LocalTime.parse("18:55:05"));
        System.out.println("时间相差1 Duration：" + d1);  //PT5H9M45S

        //Duration:LocalDateTime
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDate date6 = LocalDate.parse("2014-04-18");
        LocalTime time6 = LocalTime.parse("12:25:28");
        LocalDateTime dt6 = LocalDateTime.of(date6, time6);
        d1 = Duration.between(dt1, dt6);
        System.out.println("时间相差2 Duration：" + d1);  //PT742H40M8S   这里相关的天转为了小时

        //Duration:Instant
        d1 = Duration.between(Instant.ofEpochSecond(300), Instant.now());
        System.out.println("时间相差3 Duration：" + d1);

        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println("时间相差4 Duration：" + twoYearsSixMonthsOneDay);
        Period tenDays = Period.ofDays(10);
        System.out.println("时间相差7 Duration：" + tenDays);
        Period threeWeeks = Period.ofWeeks(3);
        System.out.println("时间相差8 Duration：" + threeWeeks);
        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println("时间相差5 Duration：" + threeMinutes);
        threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println("时间相差6 Duration：" + threeMinutes);

        LocalDate startDate = LocalDate.of(2017, 3, 19);
        System.out.println("开始时间  : " + startDate);

        LocalDate endDate = LocalDate.of(2018, 3, 25);
        System.out.println("结束时间 : " + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);
        long monthsDiff = ChronoUnit.MONTHS.between(startDate, endDate);
        System.out.println("两天之间的差在月数   : " + monthsDiff);
    }
}
