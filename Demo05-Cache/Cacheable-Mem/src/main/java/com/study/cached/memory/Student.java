package com.study.cached.memory;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/10/1.
 */
@Component
public class Student {
    @Cacheable(value = "stuname", key = "#name")
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

    @CachePut(value = "stuname", key = "#name")
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

    @CacheEvict("stuname")
    public void remove(String name)
    {

    }
}
