package com.study.base.文本处理_加密解密security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author sk-qianxiao
 * @date 2020/1/15
 */
public class Md5Test {
    public static void main(String[] args) {
        String input = "123456";

        byte[] byteArray = toMD5Bytes(input);
        String output = toHexString(byteArray);
        System.out.println(output);
    }

    private static byte[] toMD5Bytes(String input) {
        byte[] byteArray = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(input.getBytes("UTF-8"));

            //进行MD5转换
            byteArray = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return byteArray;
    }


    /**
     * 转16进程
     *
     * @param byteArray
     * @return
     */
    private static String toHexString(byte[] byteArray) {
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }

        }
        return md5StrBuff.toString().toUpperCase();
    }
}
