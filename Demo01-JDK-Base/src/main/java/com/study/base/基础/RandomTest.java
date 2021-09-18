package com.study.base.基础;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Created by xq on 2017/9/23.
 */
public class RandomTest {
    private static Logger LOGGER = LoggerFactory.getLogger(RandomTest.class);

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        uuidTest();
        mathRandom();
        randomTest();
    }

    public static void uuidTest() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }

    public static void mathRandom() {
        double num = Math.random() * 100;
        LOGGER.error("Math.random() * 100=>{}", num);
    }

    public static void randomTest() {
        Random random = new Random();

        System.out.print("1-10的Double");
        for (int i = 0; i < 10; i++) {
            System.out.print(random.nextInt(10) + " ");
        }
        System.out.println(" ");

        System.out.print("1-10的整数");
        for (int i = 0; i < 10; i++) {
            System.out.print(random.nextDouble() + " ");
            random.doubles();
        }
        System.out.println(" ");

    }
}
