package com.xq.study.anocation.fruitanocation;

import java.lang.annotation.*;

/**
 * Created by xq on 2017/10/24.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "Pankaj";

    String date();

    int revision() default 1;

    String comments();
}