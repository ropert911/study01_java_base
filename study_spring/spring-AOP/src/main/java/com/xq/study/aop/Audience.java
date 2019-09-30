package com.xq.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 */
@Aspect
@Component
public class Audience {
    /**声明切点*/
    @Pointcut("execution(* com.xq.study.aop.Performance.performance(int)) && args(number)")
    public void performance(int number) {
    }

//    @Before(performance(number) *)        //before方法
//    @After(performance(number) *)         //after方法

    @Around("performance(number) *")
    public void wartchPer(ProceedingJoinPoint jp, int number) {
        try {
            System.out.println("Audicence: 就坐!");
            System.out.println("Audicence: 安静!");

            System.out.println("Audicence: 看到 " + number + "演员");
            jp.proceed();

            System.out.println("Audicence: 鼓掌!");
        } catch (Throwable e) {
            System.out.println("Audicence: 表演不好，还钱");
        }
    }
}
