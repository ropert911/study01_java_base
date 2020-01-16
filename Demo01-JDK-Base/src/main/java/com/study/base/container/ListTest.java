package com.study.base.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class ListTest {
    /**
     * list 转 数组
     */
    public static void main(String[] args) {
        testList2Array();
        testList2Set();
    }
    public  static void testList2Array() {
        //List-->数组
        List<String> list = new ArrayList<String>();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        Object[] objects = list.toArray();//返回Object数组

        String[] arr = new String[list.size()];
        list.toArray(arr);//将转化后的数组放入已经创建好的对象中
    }

    /**
     * list 转 set
     */
    public static void testList2Set() {

        List<String> list = new ArrayList<String>();
        list.add("ABC");
        list.add("EFG");
        list.add("LMN");
        list.add("LMN");

        //List-->Set
        Set<String> listSet = new HashSet<String>(list);
        System.out.println(listSet);
    }

}
