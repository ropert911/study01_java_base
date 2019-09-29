package com.xq.study.anocation.aop;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface MethodLimit {
    /**
     *
     * 允许访问的次数，默认值MAX_VALUE
     */
    int count() default Integer.MAX_VALUE;

    /**
     *
     * 时间段，单位为毫秒，默认值一分钟
     */
    long time() default 60000;
}
