package com.study.lombok;

import com.study.lombok.examples.DataExample;
import com.study.lombok.examples.GetterSetterExample;
import com.study.lombok.examples.NonNullExample;
import com.study.lombok.examples.ToStringExample;
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
