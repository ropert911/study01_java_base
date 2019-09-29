package com.xq.study.jdk2.reflect;

/**
 * Created by xq on 2018/6/24.
 */
public class StaticNestedClass {
    public StaticNestedClass(String name) {
        System.out.println("1111");
    }

    class NestedClass {
        public NestedClass(int count) {
            System.out.println("2222");
        }
    }
}
