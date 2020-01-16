package com.study.guava;

import com.google.common.cache.Cache;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author sk-qianxiao
 */
@SpringBootApplication
public class DemoGuavaApplication implements ApplicationRunner {
    @Resource(name = "cachedAge")
    private Cache<String, Integer> cachedAge;

    public static void main(String[] args) {
        SpringApplication.run(DemoGuavaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cachedAge.put("xiaoqian", 35);
        Integer age = cachedAge.getIfPresent("xiaoqian");
        if (null != age) {
            System.out.println("get from cache 1=="+age);
        }
        age = cachedAge.getIfPresent("xiaoqian2");
        if (null != age) {
            System.out.println("get from cache 2=="+age);
        }
    }
}
