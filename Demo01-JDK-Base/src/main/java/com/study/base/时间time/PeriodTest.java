package com.study.base.时间time;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Created by xq on 2017/9/23.
 */
public class PeriodTest {
    public static void main(String[] args){
        durationTest();
    }

    /**
     * Duration时间 Period日期 差
     */
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
