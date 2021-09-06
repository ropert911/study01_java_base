package com.study.base.container;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author sk-qianxiao
 * @date 2021/9/6
 */
public class BiMapTest {
    public static void main(String[] args) {
        //BiMap 一种连value也不能重复的HashMap


        BiMap<String, String> biMap = HashBiMap.create();
        // 如果value重复，put方法会抛异常，除非用forcePut方法
        biMap.put("key", "value");
        System.out.println(biMap); // 输出 {"key":"value"}

        // 既然value不能重复，何不实现个翻转key/value的方法，已经有了
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse); // 输出 {"value":"key"}
    }
}
