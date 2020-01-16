package com.study.guava.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author sk-qianxiao
 */
@Configuration
public class Config {
    @Bean(name = "cachedAge")
    public Cache<String, Integer> cacheManager() {
        Cache<String, Integer> cache = CacheBuilder.newBuilder()
                /**设置缓存的最大容量*/
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .concurrencyLevel(10)
                .recordStats()
                .build();
        return cache;
    }
}
