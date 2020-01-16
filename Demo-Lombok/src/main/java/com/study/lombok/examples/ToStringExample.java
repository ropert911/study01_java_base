package com.study.lombok.examples;

import com.study.lombok.models.Shape;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="id")
public class ToStringExample {
    private static final int STATIC_VAR = 10;
    @Setter
    private String name;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    @Setter
    private int id;


    @ToString(callSuper=false, includeFieldNames=true)  //toString不要父类,tostring包含字段名
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}