package com.study.base.数据处理_容器container;

import java.util.*;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class SetTest {
    public static void main(String[] args) {
        //Set 转 数据
        testSet2Array();
        //set 转 list
        testSet2List();
    }

    /**
     * Set 转 数据
     */
    public static void testSet2Array() {
        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        String[] arr = new String[set.size()];
        //Set-->数组
        set.toArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * set 转 list
     */
    public static void testSet2List() {

        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        //Set --> List
        List<String> setList = new ArrayList<String>(set);
        System.out.println(setList);
    }
}
