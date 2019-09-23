package com.study.xq.demolombok;

import com.study.xq.demolombok.examples.DataExample;
import com.study.xq.demolombok.examples.GetterSetterExample;
import com.study.xq.demolombok.examples.NonNullExample;
import com.study.xq.demolombok.examples.ToStringExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoLombokApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLombokApplication.class, args);

        DataExample dataExample = DataExample.builder().name("xq").age(34).build();
        dataExample.setScore(11);
        System.out.println(dataExample);

        GetterSetterExample getterSetterExample = new GetterSetterExample();
        getterSetterExample.setAge(12);
        System.out.println(getterSetterExample);

        ToStringExample toStringExample = new ToStringExample();
        toStringExample.setId(1);
        toStringExample.setName("xq");
        System.out.println(toStringExample);

        NonNullExample nonNullExample = new NonNullExample("xq");
        System.out.println(nonNullExample);
    }

}
