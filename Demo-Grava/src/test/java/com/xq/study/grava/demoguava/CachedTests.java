package com.xq.study.grava.demoguava;

import com.google.common.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author sk-qianxiao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CachedTests {
    @Resource(name = "cachedAge")
    private Cache<String, Integer> cachedAge;

    @Test
    public void test1() {
        cachedAge.put("xiaoqian", 35);
        Integer age = cachedAge.getIfPresent("xiaoqian");
        if (null != age) {
            System.out.println(age);
        }
        age = cachedAge.getIfPresent("xiaoqian2");
        if (null != age) {
            System.out.println(age);
        }
    }
}
