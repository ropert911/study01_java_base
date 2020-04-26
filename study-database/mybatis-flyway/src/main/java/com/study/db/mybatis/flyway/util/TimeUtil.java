package com.skspruce.homeap.dcm.server.util;

import java.sql.Timestamp;

/**
 * 时间工具
 *
 * @author sk-qianxiao
 * @date 2020/4/17
 */
public class TimeUtil {

    /**
     * 当前时间转TimeStamp格式
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return now;
    }

    /**
     * 获取N天前的TimeStamp
     *
     * @param n
     * @return
     */
    public static Timestamp getNDayBeforTeimestamp(int n) {
        Timestamp tp = new Timestamp(System.currentTimeMillis() - 86400000L * n);
        return tp;
    }

    /**
     * 获取N分钟的TimeStamp
     *
     * @param n
     * @return
     */
    public static Timestamp getNMinBeforTeimestamp(int n) {
        Timestamp tp = new Timestamp(System.currentTimeMillis());
        Long lg = tp.getTime() - 60000 * n;
        Timestamp endCreateTime = new Timestamp(lg);
        return endCreateTime;
    }
}
