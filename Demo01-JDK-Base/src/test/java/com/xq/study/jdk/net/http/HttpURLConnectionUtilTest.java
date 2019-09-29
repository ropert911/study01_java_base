package com.xq.study.jdk.net.http;

import org.junit.Assert;
import org.junit.Test;

public class HttpURLConnectionUtilTest {
    @Test
    public void test1(){
        System.out.println("Testing 1 - Send Http GET request");
        String value = HttpURLConnectionUtil.doGet("http://www.baideu.com");
        Assert.assertNotNull(value);

        System.out.println("\nTesting 2 - Send Http POST request");
        value = HttpURLConnectionUtil.doPost("https://selfsolve.apple.com/wcResults.do", "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345");
        Assert.assertNotNull(value);
    }
}