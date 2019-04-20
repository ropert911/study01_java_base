package com.xq.study.jdk.容器;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class List_Test {
    public static void main(String arg[]){
        testList2Array();   //list 转 数组
        testList2Set();     // list 转 set
    }

    private static void testList2Array() {
        //List-->数组
        List<String> list = new ArrayList<String>();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        Object[] objects = list.toArray();//返回Object数组

        String[] arr = new String[list.size()];
        list.toArray(arr);//将转化后的数组放入已经创建好的对象中
    }

    private static void testList2Set() {

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
