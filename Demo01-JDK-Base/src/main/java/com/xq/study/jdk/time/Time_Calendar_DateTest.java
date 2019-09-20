package com.xq.study.jdk.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by xq on 2017/9/23.
 */
public class Time_Calendar_DateTest {
    private static Logger LOGGER = LoggerFactory.getLogger(Time_Calendar_DateTest.class);

    public static void main(String[] args) {
        System.out.println("============== 日历测试");
        calendarTest();
        dateTest();

        test1();
    }

    private static void calendarTest() {
        /*
        java.util.GregorianCalendar
        [time=1506124495243,                            //utc时间，单位毫秒
        areFieldsSet=true,areAllFieldsSet=true,
        lenient=true,
        zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null],
        firstDayOfWeek=1,
        minimalDaysInFirstWeek=1,
        ERA=1,
            YEAR=2017,
            MONTH=8,                //月是从零开始
            WEEK_OF_YEAR=38,WEEK_OF_MONTH=4,        //从1开始
                DAY_OF_MONTH=23,DAY_OF_YEAR=266,DAY_OF_WEEK=7,DAY_OF_WEEK_IN_MONTH=4,
            AM_PM=0,HOUR=7,HOUR_OF_DAY=7,MINUTE=54,SECOND=55,MILLISECOND=243,
            ZONE_OFFSET=28800000,DST_OFFSET=0]      // ZONE_OFFSET=28800000  偏移的毫秒数
        */

        LOGGER.error("================show calendar");
        Calendar calendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历
        LOGGER.error("Calendar 信息 {}", calendar);
        TimeZone zone = TimeZone.getTimeZone("GMT-0:00");
        calendar = Calendar.getInstance(zone); //使用UTC时区获取 日历

        //转Date
        Date date = calendar.getTime();

        //修改日期
        calendar.setTime(new Date());

        printCalendar(calendar);
    }

    private static void dateTest() {
        LOGGER.error("============= show Date");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");//设置日期时间格式
        LOGGER.error("日期格式化输出=>{}", df.format(date));
    }

    private static void printCalendar(Calendar time) {
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;
        int date = time.get(Calendar.DATE);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);
        int milsecond = time.get(Calendar.MILLISECOND);
        LOGGER.error(year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second + ":" + milsecond);

        int weekno1 = time.get(Calendar.WEEK_OF_YEAR);
        int weekno2 = time.get(Calendar.WEEK_OF_MONTH);
        LOGGER.error("这是一年的第 " + weekno1 + " 个星期 这是一月的第 " + weekno2 + " 个星期 ");
        weekno1 = time.getActualMaximum(Calendar.WEEK_OF_YEAR);
        weekno2 = time.getActualMaximum(Calendar.WEEK_OF_MONTH);
        LOGGER.error("本年有 " + weekno1 + " 个星期 本月有 " + weekno2 + " 个星期 ");

        int day1 = time.get(Calendar.DAY_OF_YEAR);  //获得本月份的天数
        int day2 = time.get(Calendar.DAY_OF_MONTH);
        LOGGER.error("本年的第：" + day1 + "天 本月的第：" + day2 + "天");
        day1 = time.getActualMaximum(Calendar.DAY_OF_YEAR); //获得本月份的天数
        day2 = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        LOGGER.error("本年的天数：" + day1 + " 本月的天数：" + day2);

        long secs = time.getTimeInMillis();
        LOGGER.error("毫秒数：" + secs);
    }

    public static void test1() {
        //取得当前的Hijrah 日期，紧接着对其进行修正，得到斋月的第一天，即第9个月
        HijrahDate ramadanDate = HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("Ramadan starts on " +
                IsoChronology.INSTANCE.date(ramadanDate) +
                " and ends on " +
                IsoChronology.INSTANCE.date(
                        ramadanDate.with(
                                TemporalAdjusters.lastDayOfMonth())));
    }
}
