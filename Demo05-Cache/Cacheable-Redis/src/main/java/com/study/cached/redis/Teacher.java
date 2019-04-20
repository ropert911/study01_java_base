package com.study.cached.redis;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/10/1.
 */
@Component
@CacheConfig(cacheNames = "stuname")
public class Teacher {
    @Cacheable(key = "#name")
    public int getAge(String name){
        System.out.println("getAge call");
        if(name.equals("name1")){
            return 1;
        }else if(name.equals("name2")){
            return 2;
        }else{
            return 3;
        }
    }

    @CachePut(key = "#name")
    public int updateAge(String name){
        System.out.println("updateAge call");
        if(name.equals("name1")){
            return 11;
        }else if(name.equals("name2")){
            return 22;
        }else{
            return 33;
        }
    }

    @CacheEvict(key = "#name")
    public void remove(String name)
    {

    }

    @Cacheable(keyGenerator="keyGenerator")
    public String test(){
        System.out.println("test call");
        return "肖迁";
    }
}
