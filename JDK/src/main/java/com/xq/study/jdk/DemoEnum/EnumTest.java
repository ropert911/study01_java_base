package com.xq.study.jdk.DemoEnum;

/**
 * Created by sk-qianxiao on 2017/11/20.
 */

enum Sex {
    SEX_MALE("男"),//男
    SEX_FEMALE("女"),//女
    SEX_UNKNOWN("人妖"); //人妖
    private final String text;

    private Sex(final String text) {
        this.text = text;
    }

    public static Sex getEnumFromString(String string) {
        if (string != null) {
            try {
                return Enum.valueOf(Sex.class, string.trim());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return text;
    }
}


public class EnumTest {

    public static void main(String[] args) {
        Sex mySex1 = Sex.getEnumFromString("SEX_MALE");
        System.out.println("index name = " + mySex1);
        String mySex = Sex.SEX_MALE.toString();
        System.out.println("index name = " + mySex);

        Sex se = Sex.SEX_FEMALE;
        switch (se) {
            case SEX_MALE: {
                System.out.println("11111111");
                break;
            }
            case SEX_FEMALE: {
                System.out.println("22222222");
                break;
            }
        }
    }
}
