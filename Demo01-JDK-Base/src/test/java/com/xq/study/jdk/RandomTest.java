package com.xq.study.jdk;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by xq on 2017/9/23.
 */
public class RandomTest {
    private static Logger LOGGER = LoggerFactory.getLogger(RandomTest.class);

    @Test
    public  void test1() {
        double num = Math.random() * 100;
        LOGGER.error("Math.random() * 100=>{}", num);

        Random r3 = new Random();

        LOGGER.info("生成的[0,10)之间的整数");
        for (int i = 0; i < 10; i++) {
            System.out.print(r3.nextInt(10) + " ");
        }
        System.out.println(" ");

        LOGGER.info("生成的[0,1)之间的double");
        for (int i = 0; i < 10; i++) {
            System.out.print(r3.nextDouble() + " ");
            r3.doubles();
        }
        System.out.println(" ");
    }
}
