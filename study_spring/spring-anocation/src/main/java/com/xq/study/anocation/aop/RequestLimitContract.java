package com.xq.study.anocation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLimitContract {
    private static final Logger logger = LoggerFactory.getLogger("RequestLimitLogger");


    @Before("within(com.xq.study.anocation.aop.MyUser) && @annotation(RequestLimit)")
    public void requestLimit(final JoinPoint joinPoint, MethodLimit RequestLimit) throws RequestLimitException {
        System.out.println("切面函数被调用");
        throw new RequestLimitException("请求次数超过限定");
    }
}