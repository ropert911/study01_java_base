package com.xq.study.compress;


import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        String value = "Hello world";
        try {
            byte[] bytes = GzipUtil.compress(value.getBytes());
            byte[] orData = GzipUtil.decompress(bytes);
            System.out.println(new String(orData));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
