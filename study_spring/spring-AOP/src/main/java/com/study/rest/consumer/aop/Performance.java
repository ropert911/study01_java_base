package com.study.rest.consumer.aop;

import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 */
@Component
public class Performance {
    public void performance(){
        System.out.println("********** performance **************");
    }
    public void performance(int performancer){
        System.out.println("********** performance **************");
    }
}
