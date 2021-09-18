package com.study.base.时间time;

import java.time.LocalTime;

/**
 * Created by xq on 2017/9/23.
 */
public class LocalTimeTest {
    public static void main(String[] args){
        localTimeTest();
    }

    /**
     * LocalTime time
     */
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
}
