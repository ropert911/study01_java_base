package com.study.spring.bean;

import com.study.spring.bean.service.DiConfig;
import com.study.spring.bean.service.UseFunctionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("Java config"));

        context.close();
    }
}
