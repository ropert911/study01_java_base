package com.study.base.string;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xq
 * @data 2020/4/27
 **/
public class StringBuilderBufferTest {
    public static void main(String[] args) {
        testStringBuffer();
        testStringBuilder();
        translate();
        testStringUtils();
    }

    static void testStringBuffer() {
        int loop = 10000;
        //线程安全的
        StringBuffer stringBuffer = new StringBuffer();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            stringBuffer.append("s");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("时间=" + (endTime - startTime) + "ms");
    }

    static void testStringBuilder() {
        int loop = 10000;
        //没有实现线程安全
        StringBuilder stringBuilder = new StringBuilder();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            stringBuilder.append("s");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("时间=" + (endTime - startTime) + "ms");
    }

    static void translate() {
        int a = 1;
        long b = 1;
        double c = 1;
        boolean d = true;
        String s = String.valueOf(a);
        int i = Integer.valueOf(s);
    }

    static void testStringUtils() {
        List<String> list = new ArrayList<>();
        list.add("Hollis");
        list.add("每日更新java相关技术");
        System.out.println(StringUtils.join(new ArrayList<String>(list), ","));
    }
}
