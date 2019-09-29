package com.xq.study.jdk.container;

import org.junit.Test;

import java.util.*;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class SetTest {
    /**
     * Set 转 数据
     */
    @Test
    public void testSet2Array() {
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
    @Test
    public void testSet2List() {

        Set<String> set = new HashSet<String>();
        set.add("AA");
        set.add("BB");
        set.add("CC");

        //Set --> List
        List<String> setList = new ArrayList<String>(set);
        System.out.println(setList);
    }
}
