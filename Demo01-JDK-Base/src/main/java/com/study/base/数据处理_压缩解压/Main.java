package com.study.base.数据处理_压缩解压;


import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        String value = "Hello world";
        try {
            //数据压缩
            byte[] bytes = GzipUtil.compress(value.getBytes());
            //数据解压
            byte[] orData = GzipUtil.decompress(bytes);
            System.out.println(new String(orData));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
