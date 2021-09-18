package com.study.base.数据处理_容器container;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class ListTest {
    /**
     * list 转 数组
     */
    public static void main(String[] args) {
        //list转数组
        DataArrayTest.testList2Array();
        //list转set
        testList2Set();
        //list转Map
        testList2Map();
        //元素拼接
        testListJoin();
        //交集--会改变原list
        testListInnerJoin();
        //交集、并集、差集 - 不改变原list
        testListUnion();
    }

    public static void testListJoin() {
        List<String> list = Arrays.asList("a", "b", "c");
        {
            // 第一种方法，可以用stream流
            String join = list.stream().collect(Collectors.joining(","));
            System.out.println("List<String> 元素拼接: " + join);
        }
        {
            // 第二种方法，其实String也有join方法可以实现这个功能
            String join = String.join(",", list);
            System.out.println("List<String> 元素拼接: " + join);
        }
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
        System.out.println("List<String> 转Set<String> " + listSet);
    }


    public static void testList2Map() {
        List<String> list = new ArrayList<String>();
        list.add("ABC");
        list.add("EFG");
        list.add("LMN");
        list.add("LMN");

        //List-->map
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(String::length, a -> a));
        System.out.println("List<String> 转Map<Integer,String> " + map);
    }

    public static void testListInnerJoin() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");
        list1.retainAll(list2);
        System.out.println("List<String> 保留另一个list有的元素: " + list1);
    }

    public static void testListUnion() {
        List<String> listA = new ArrayList<>();
        listA.add("a");
        listA.add("b");
        listA.add("c");
        List<String> listB = new ArrayList<>();
        listB.add("a");
        listB.add("b");
        listB.add("d");
        {
            // 两个集合取交集
            Collection<String> collection = CollectionUtils.retainAll(listA, listB);
            System.out.println("交集  " + collection);
        }
        {
            // 两个集合取并集
            Collection<String> collection = CollectionUtils.union(listA, listB);
            System.out.println("并集  " + collection);
        }
        {
            // 两个集合取差集
            Collection<String> collection = CollectionUtils.subtract(listA, listB);
            System.out.println("差集  " + collection);
        }
    }
}
