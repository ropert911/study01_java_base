package com.study.base.文本处理_通用string;

/**
 * @author xq
 * @data 2020/4/27
 **/
public class StringBuilderBufferTest {
    public static void main(String[] args) {
        //StringBuffer 是线程安全的
        testStringBuffer();
        //StringBuilder 非线程安全的
        testStringBuilder();
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
}
