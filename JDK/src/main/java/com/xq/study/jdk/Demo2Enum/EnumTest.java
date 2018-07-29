package com.xq.study.jdk.Demo2Enum;

/**
 * Created by sk-qianxiao on 2017/11/20.
 */

enum IndexEnum {
    USAGE,
    FAFA,
    SEX_UNKNOWN; //人妖

    public static IndexEnum getEnumFromString(String string) {
        if (string != null) {
            try {
                return Enum.valueOf(IndexEnum.class, string.trim());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name();
    }
}


public class EnumTest {

    public static void main(String[] args) {
        IndexEnum index1 = IndexEnum.getEnumFromString("USAGE");
        System.out.println("index name = " + index1 + index1.toString());

        String index2 = IndexEnum.USAGE.toString();
        System.out.println("index name = " + index2);

        IndexEnum se = IndexEnum.USAGE;
        switch (se) {
            case USAGE: {
                System.out.println("11111111");
                break;
            }
            case FAFA: {
                System.out.println("22222222");
                break;
            }
        }
    }
}
