package com.study.spring.bean.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xq on 2017/11/15.
 */
@Configuration
public class DiConfig {
    @Bean
    FunctionService createFunctionService(){
        return new FunctionService();
    }
    @Bean
    UseFunctionService createUseFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        //这里createFunctionService会直接使用之前建的那个bean，不会再创建一次
        useFunctionService.setFunctionService(createFunctionService());
        return useFunctionService;
    }
}
