package com.xq.study.jdk.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sk-qianxiao on 2017/10/19.
 */
public class IndexJob implements SimpleJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        //当前时间：忽略 秒 毫秒
        LocalDateTime localDateTime = LocalDateTime.now().withSecond(0).withNano(0);
        //已入库完成的时间对应的最终时间
        LocalDateTime endTime = localDateTime.minusMinutes(TriggerCron.getInterVal() + 3);

        //指定时区
        ZonedDateTime zonedDateTime = endTime.atZone(ZoneId.systemDefault());
        //转UTC的时间
        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LOGGER.error("日期格式化输出=>{}-{}-{} {}:{}:{}", zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth(), zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond());

        LOGGER.error("整点统计触发-时钟偏移：{}分钟", (60 - zonedDateTime.getMinute()) % 60);

        //转为当地时间后，再判断是不是应该做日、周、月、年 统计
        ZoneOffset localZoneOffSet = ZoneOffset.ofTotalSeconds(28800);
        zonedDateTime = zonedDateTime.withZoneSameInstant(localZoneOffSet);
        LOGGER.error("指定时区 日期格式化输出=>{}-{}-{} {}:{}:{}", zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth(), zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond());
        if (0 == zonedDateTime.getHour()) {
            LOGGER.error("日统计触发");
            if (1 == zonedDateTime.getDayOfMonth()) {
                LOGGER.error("月统计触发");
                if (1 == zonedDateTime.getMonthValue()) {
                    LOGGER.error("年统计触发");
                }
            }

            if (DayOfWeek.MONDAY == zonedDateTime.getDayOfWeek()) {
                LOGGER.error("周统计触发");
            }
        }
    }
}