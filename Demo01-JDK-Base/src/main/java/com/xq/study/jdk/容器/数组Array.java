package com.xq.study.jdk.容器;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xq on 2019/2/27.
 */
public class 数组Array {
    public static void main(String[] args) {
        useArray();

        testArray2Set(); //数组转Set
        testArray2List();   //数组转list
    }

    private static void testArray2Set() {
        String[] arr = {"AA", "BB", "DD", "CC", "BB"};

        //数组-->Set
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        System.out.println(set);
    }

    private static void testArray2List() {
        //数组-->List
        String[] ss = {"JJ", "KK"};
        List<String> list1 = Arrays.asList(ss);
        List<String> list2 = Arrays.asList("AAA", "BBB");
        System.out.println(list1);
        System.out.println(list2);
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
