package com.xq.study.jdk.container;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataArrayTest {
    @Test
    public void useArray() {
        int[][][] matrix1 = (int[][][]) Array.newInstance(int.class, 3, 3, 3);
        int[][][] matrix2 = (int[][][]) Array.newInstance(int[].class, 3, 3);
        String[] names = (String[]) Array.newInstance(String.class, 3);
        names[0] = "hello";
        Array.set(names, 1, "world");

        String str = (String) Array.get(names, 0);
        System.out.println(str);
        for (String name : names) {
            System.out.print(name + " ");
        }
    }

    @Test
    public void array2SetTest() {
        String[] arr = {"AA", "BB", "DD", "CC", "BB"};

        //数组-->Set
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        System.out.println(set);
    }

    @Test
    public void array2ListTest() {
        //数组-->List
        String[] ss = {"JJ", "KK"};
        List<String> list1 = Arrays.asList(ss);
        List<String> list2 = Arrays.asList("AAA", "BBB");
        System.out.println(list1);
        System.out.println(list2);
    }
}
