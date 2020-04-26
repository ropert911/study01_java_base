package com.study.base.string;

/**
 * @author xq
 * @data 2020/4/27
 **/
public class StringBuilderBufferTest {
    public static void main(String[] args) {
        int loop = 10000;
        //线程安全的
        StringBuffer stringBuffer = new StringBuffer();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            stringBuffer.append("s");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("时间=" + (endTime - startTime) + "ms");

        //没有实现线程安全
        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            stringBuilder.append("s");
        }
        endTime = System.currentTimeMillis();
        System.out.println("时间=" + (endTime - startTime) + "ms");
    }
}
