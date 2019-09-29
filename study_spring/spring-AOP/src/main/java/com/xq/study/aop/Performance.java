package com.xq.study.aop;

import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/10/1.
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
