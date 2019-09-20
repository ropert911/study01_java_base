package com.xq.study.jdk.container;

import java.util.*;

/**
 * Created by sk-qianxiao on 2019/3/4.
 */
public class Map_Test {
    public static void main(String arg[]) {
        transfor();//Map 遍历

        testMap2List(); //Map 转 list
        testMap2Set();  //Map 转 Set
    }

    private static void transfor() {
        Map<Integer, Integer> map = new HashMap<>();
        //这是最常见的并且在大多数情况下也是最可取的遍历方式。在键值都需要时使用
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key=" + entry.getKey() + ",Value=" + entry.getValue());
        }

        //遍历key
        for (Integer key : map.keySet()) {
            System.out.println("key=" + key);
        }

        //遍历value
        for (Integer val : map.values()) {
            System.out.println("val=" + val);
        }

        //iterfator遍历-不使用泛型
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key = (Integer) entry.getKey();
            Integer val = (Integer) entry.getValue();
        }

        //iterfator遍历-使用泛型
        Iterator<Map.Entry<Integer, Integer>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator2.next();
            Integer key = entry.getKey();
            Integer val = entry.getValue();
        }
    }

    private static void testMap2List() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "ABC");
        map.put("K", "KK");
        map.put("L", "LV");

        // 将Map Key 转化为List
        List<String> mapKeyList = new ArrayList<String>(map.keySet());
        System.out.println("mapKeyList:" + mapKeyList);

        // 将Map Key 转化为List
        List<String> mapValuesList = new ArrayList<String>(map.values());
        System.out.println("mapValuesList:" + mapValuesList);
    }

    private static void testMap2Set() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "ABC");
        map.put("K", "KK");
        map.put("L", "LV");

        // 将Map 的键转化为Set
        Set<String> mapKeySet = map.keySet();
        System.out.println("mapKeySet:" + mapKeySet);

        // 将Map 的值转化为Set
        Set<String> mapValuesSet = new HashSet<String>(map.values());
        System.out.println("mapValuesSet:" + mapValuesSet);
    }

}
