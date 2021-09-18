package com.study.base.文本处理_通用string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author xq
 * @data 2020/4/29
 **/
public class StringSplit {
    public static void main(String[] args) {
        //字符串分割和合并
        splitJoin();
    }

    private static void splitJoin() {
        //传入的分隔字符串是正则表达式，则部分关键字（比如 .[]()| 等）需要转义
        String[] split = "a.ab.abc".split("\\.");
        System.out.println(Arrays.toString(split));

        String[] split1 = "a|ab|abc".split("\\|");
        System.out.println(Arrays.toString(split1));

        String join = String.join("-", split1);
        System.out.println("String.join按-合并: " + join);

        String join2 = StringUtils.join(split1, ",");
        System.out.println("StringUtils.join按,合并: " + join2);
    }
}
