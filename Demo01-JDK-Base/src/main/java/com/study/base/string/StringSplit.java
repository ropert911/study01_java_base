package com.study.base.string;

import java.util.Arrays;

/**
 * @author xq
 * @data 2020/4/29
 **/
public class StringSplit {
    public static void main(String[] args) {
        //传入的分隔字符串是正则表达式，则部分关键字（比如 .[]()| 等）需要转义
        String[] split = "a.ab.abc".split("\\.");
        System.out.println(Arrays.toString(split));

        String[] split1 = "a|ab|abc".split("\\|");
        System.out.println(Arrays.toString(split1));
    }
}
