package com.study.base.container;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * @author sk-qianxiao
 * @date 2021/9/6
 */
public class MultisetTest {
    public static void main(String[] args) {
        //Multiset 一种用来计数的Set
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("apple");
        multiset.add("apple");
        multiset.add("orange");
        System.out.println(multiset.count("apple")); // 输出 2

        // 查看去重的元素
        Set<String> set = multiset.elementSet();
        // 输出 ["orange","apple"]
        System.out.println("按去重方式查看 " + set);

        // 还能查看没有去重的元素
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 还能手动设置某个元素出现的次数
        multiset.setCount("apple", 5);
    }
}
