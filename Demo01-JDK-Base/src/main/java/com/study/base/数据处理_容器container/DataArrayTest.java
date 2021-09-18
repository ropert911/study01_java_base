package com.study.base.数据处理_容器container;

import org.eclipse.jetty.util.ajax.JSON;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataArrayTest {
    public static void main(String[] args) {
        //生成数组
        newArray();
        //List转数组
        testList2Array();
        //数组转List
        testArray2List();
        //数据转Set
        testArray2Set();
    }

    public static void newArray() {
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

    public static void testList2Array() {
        {
            List<Long> userIdList = Arrays.asList(1L, 2L, 3L);
            long[] userIdArray = userIdList.stream().mapToLong(Long::longValue).toArray();
            System.out.println("List<Long> 转 long[] " + JSON.toString(userIdArray));
        }
        {
            List<String> list = Arrays.asList("AA", "BB", "CC");
            String[] arr = list.toArray(new String[list.size()]);
            System.out.println("List<String> 转 String[] " + JSON.toString(arr));
        }
    }

    public static void testArray2List() {
        {
            String[] ss = {"JJ", "KK"};
            List<String> list1 = Arrays.asList(ss);
            System.out.println("数组 转 List<String> " + list1);
        }
        {
            List<String> list2 = Arrays.asList("AAA", "BBB");
            System.out.println("数组 转 List<String> " + list2);
        }
    }

    public static void testArray2Set() {
        {
            String[] arr = {"AA", "BB", "DD", "CC", "BB"};
            Set<String> set = new HashSet<String>(Arrays.asList(arr));
            System.out.println("数组 转 Set<String> " + set);
        }
    }
}
