package com.xq.study.anocation.fruitanocation;

import java.lang.annotation.*;

/**
 * Created by xq on 2017/10/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    enum Color {BULE, RED, GREEN}

    ;

    Color fruitColor() default Color.GREEN;
}