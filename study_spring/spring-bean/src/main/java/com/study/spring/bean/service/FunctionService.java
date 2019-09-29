package com.study.spring.bean.service;

import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/11/15.
 */
public class FunctionService {
    FunctionService(){
        System.out.println("1111111111");
    }
    public String sayHello(String world){
        return "Hello " + world;
    }
}
