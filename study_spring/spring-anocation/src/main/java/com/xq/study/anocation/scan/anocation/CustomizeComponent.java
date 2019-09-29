package com.xq.study.anocation.scan.anocation;

import java.lang.annotation.*;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeComponent {
    String value() default "";
}