package com.study.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;

public class TestApplication {
    private static Logger logger = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) {
        char[] src = new char[]{'a', 'b', 'c', 'd', 'e'};
        char[] des = new char[5];
        System.arraycopy(src, 0, des, 0, 4);
        System.out.println(des);
    }
}