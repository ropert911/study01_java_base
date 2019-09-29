package com.xq.study.anocation.fruitanocation;

/**
 * Created by xq on 2017/10/24.
 */

import java.lang.annotation.*;

/**
 * 水果供应者注解
 *
 * @author peida
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    int id() default -1;

    String name() default "";

    String address() default "";
}