package com.study.base.基础.反射reflect.model;

/**
 * @author sk-qianxiao
 * Created by xq on 2018/6/24.
 */
public class StaticNestedClass {
    public StaticNestedClass(String name) {
        System.out.println("1111");
    }

    public class NestedClass {
        public NestedClass(int count) {
            System.out.println("2222");
        }
    }
}
