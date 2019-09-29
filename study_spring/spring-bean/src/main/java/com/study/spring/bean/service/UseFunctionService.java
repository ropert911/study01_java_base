package com.study.spring.bean.service;

/**
 * Created by xq on 2017/11/15.
 */

public class UseFunctionService {
    FunctionService functionService;

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public String sayHello(String world){
        return functionService.sayHello(world);
    }
}
