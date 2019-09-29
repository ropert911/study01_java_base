package com.xq.study.jdk.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Created by xq on 2017/9/23.
 */
public class InstantTest {
    /**
     * 机器时间Instan
     */
    @Test
    public void instantTest() {
        //下面几个都相同
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));

        System.out.println(Instant.now());
    }
}
