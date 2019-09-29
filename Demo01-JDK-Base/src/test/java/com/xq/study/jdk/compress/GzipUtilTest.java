package com.xq.study.jdk.compress;

import org.junit.Assert;
import org.junit.Test;

import java.net.SocketException;

public class GzipUtilTest {
    @Test
    public void test1() {
        String value = "Hello world";
        try {
            byte[] bytes = GzipUtil.compress(value.getBytes());
            byte[] orData = GzipUtil.decompress(bytes);
            Assert.assertEquals(value, new String(orData));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
