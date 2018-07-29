package com.xq.study.jdk.timetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xq on 2017/9/23.
 */
public class TimeTest {
    private static Logger LOGGER = LoggerFactory.getLogger(TimeTest.class);

    private static void timeShow() {
        LOGGER.error("当前毫秒数==>{}", System.currentTimeMillis());
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

    private static void calendarShow() {
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

        LOGGER.error("====show calendar");
        Calendar calendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历
        LOGGER.error("Calendar 信息 {}", calendar);

        printCalendar(calendar);
    }

    private static void calendarShowUTC() {
        LOGGER.error("====show UTC");
        java.util.TimeZone zone = java.util.TimeZone.getTimeZone("GMT-0:00");
        java.util.Calendar calendar = java.util.Calendar.getInstance(zone);
        printCalendar(calendar);
    }

    private static void dateToCalendary() {
        LOGGER.error("====show Date->calendar");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        printCalendar(cal);
    }

    private static void showData(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");//设置日期时间格式
        LOGGER.error("日期格式化输出=>{}", df.format(date));
    }

    private static void showCurrentData() {
        LOGGER.error("====show Date");
        Date date = new Date();
        showData(date);
    }

    private static void calendaryToDate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.error("====show calendar->Date");
        Calendar time = Calendar.getInstance();
        Date date = time.getTime();
        showData(date);
    }

    public static void main(String[] args) {
        timeShow();

        calendarShow();
        calendarShowUTC();
        dateToCalendary();

        showCurrentData();
        calendaryToDate();
    }
}
