package com.xq.study;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by sk-qianxiao on 2018/10/20.
 */
public class Main {
    public static long getServerBinStartTime() {
        int min = 5;
        LocalDateTime localDateTime = LocalDateTime.now().withSecond(0).withNano(0);
        LocalDateTime needFixBinStartTime = localDateTime.minusMinutes(min * 2 + 3);
        LocalDateTime startTime = needFixBinStartTime.withMinute((needFixBinStartTime.getMinute() / min) * min);
        return startTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static void main(String arg[]) {
        System.out.println("已统计utc时间:" + getServerBinStartTime());
        return;
    }
}
