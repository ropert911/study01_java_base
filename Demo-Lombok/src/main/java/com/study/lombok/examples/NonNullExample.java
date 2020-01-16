package com.study.lombok.examples;

import lombok.NonNull;

public class NonNullExample {
    private String name;

    //参数非空校验
    public NonNullExample(@NonNull String name) {
        this.name = name;
    }
}