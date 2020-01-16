package com.study.rest.demo.dto;

import java.io.Serializable;

public class PersonScope implements Serializable{

    /**
     * 序列
     */
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 家庭地址
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDomain [name=" + name + ", age=" + age + "]";
    }

     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}