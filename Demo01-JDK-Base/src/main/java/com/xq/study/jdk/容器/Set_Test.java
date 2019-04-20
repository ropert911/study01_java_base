package com.xq.study.jdk.容器;

import java.util.*;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class Set_Test {
    public static void main(String arg[]){
        testSet2Array();//Set 转 数据
        testSet2List(); //set 转 list
    }

    private static void testSet2Array() {
        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        String[] arr = new String[set.size()];
        //Set-->数组
        set.toArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void testSet2List() {

        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        //Set --> List
        List<String> setList = new ArrayList<String>(set);
        System.out.println(setList);
    }

}
