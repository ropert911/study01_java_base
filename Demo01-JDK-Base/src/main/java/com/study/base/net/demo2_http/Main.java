package com.study.base.net.demo2_http;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Testing 1 - Send Http GET request");
        String value = HttpUrlConnectionUtil.doGet("http://www.baideu.com");
        System.out.println(value);

        System.out.println("\nTesting 2 - Send Http POST request");
        value = HttpUrlConnectionUtil.doPost("https://selfsolve.apple.com/wcResults.do", "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345");
        System.out.println(value);
    }
}
