package com.xq.study.container;

import java.util.*;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class SetTest {
    public static void main(String[] args) {
        testSet2Array();
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
     * /set 转 list
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
