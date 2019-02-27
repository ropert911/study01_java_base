package com.xq.study.jdk;

import java.lang.reflect.Array;

/**
 * Created by xq on 2019/2/27.
 */
public class 容器_数组 {
    public static void main(String[] args) {
        useArray();
    }

    public static void useArray() {
        int[][][] matrix1 = (int[][][]) Array.newInstance(int.class, 3, 3, 3);
        int[][][] matrix2 = (int[][][]) Array.newInstance(int[].class, 3, 3);
        String[] names = (String[]) Array.newInstance(String.class, 3);
        names[0] = "hello";
        Array.set(names, 1, "world");

        String str = (String) Array.get(names, 0);
        System.out.println(str);
        for (String name : names) {
            System.out.print(name+" ");
        }
    }
}
