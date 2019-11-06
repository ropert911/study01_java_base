package com.xq.study.jdk.time;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author sk-qianxiao
 * @date 2019/11/6
 */
public class TimeUtilForJavaEightTest {
    @Test
    public void testDateFmt() {
        String formatDate = TimeUtilForJavaEight.getFormatDate("2019-08-30T10:05:17+08:00", TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX);
        System.out.println(formatDate);
        Assert.assertEquals("2019-08-30T10:05:17+08:00", formatDate);
        formatDate = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX);
        System.out.println(formatDate);
    }

    @Test
    public void testDateFmtWithZone() {
        String formatDate = TimeUtilForJavaEight.getFormatDate("2019-08-30T10:05:17+07:00", "Asia/Shanghai", TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX, TimeUtilForJavaEight.PATTERN_YYYYMMDDHHMMSS);
        System.out.println(formatDate);
        Assert.assertEquals("20190830110517", formatDate);

        String formatDateHour = TimeUtilForJavaEight.getFormatDateByDefaultZone("2019-08-30 10:05:17", 9, TimeUtilForJavaEight.PATTERN_YYYY_MM_DD_HH_MM_SS, TimeUtilForJavaEight.PATTERN_YYYYMMDDHHMMSS);
        System.out.println(formatDateHour);
        Assert.assertEquals("20190830110517", formatDateHour);
    }

    @Test
    public void testDateFmtWithTimeUnit() {
        String formatDate = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYYMMDDHH);
        System.out.println(formatDate);
        String formatDateZone = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, 5, TimeUtilForJavaEight.PATTERN_YYYYMMDDHH);
        System.out.println(formatDateZone);

        String formatDateZones = TimeUtilForJavaEight.getFormatDate(System.currentTimeMillis(), TimeUnit.MILLISECONDS, TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_SSSXXX);
        System.out.println(formatDateZones);
    }

    @Test
    public void testSeconds() {
        long formatDate = TimeUtilForJavaEight.getMilliSeconds("2019-08-30T10:05:17+07:00", TimeUtilForJavaEight.PATTERN_YYYY_MM_DDTHH_MM_SS_XXX);
        System.out.println(formatDate);
        Assert.assertEquals(1567130717000L, formatDate);
    }
}
