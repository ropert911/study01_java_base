package com.study.base.时间time;

import java.time.Instant;

/**
 * Created by xq on 2017/9/23.
 */
public class InstantTest {
    public static void main(String[] args){
        instantTest();
    }
    /**
     * 机器时间Instan
     */
    public static void instantTest() {
        //下面几个都相同
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));

        System.out.println(Instant.now());
    }
}
