package com.xq.study.jdk.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sk-qianxiao on 2017/11/9.
 */
public class TriggerCron {
    private static final Logger LOGGER = LoggerFactory.getLogger(TriggerCron.class);

    public static String getCron() {
        int min = getInterVal();
        min = (min + 63) % 15;
        return "0 " + min + "/15 * * * ?";
    }

    //获取当前的时间间隔
    public static int getInterVal() {
        return 5;
    }
}
