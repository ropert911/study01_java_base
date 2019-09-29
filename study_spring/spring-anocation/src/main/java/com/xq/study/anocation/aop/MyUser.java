package com.xq.study.anocation.aop;

import org.springframework.stereotype.Component;

/**
 * Created by xq on 2017/10/24.
 */
@Component
public class MyUser {

    @MethodLimit(count = 1, time = 1)
    public void displayName() {
        System.out.println("我的名字：xxx");
    }
}