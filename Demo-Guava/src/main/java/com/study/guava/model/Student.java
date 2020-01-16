package com.study.guava.model;


import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sk-qianxiao
 */
public class Student implements Comparable<Student> {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String age;

    @Override
    public int compareTo(Student o) {
        return ComparisonChain.start()
                .compare(this.name, o.name)
                .compare(this.address, o.address)
                .compare(this.age, o.age, Ordering.natural().nullsLast())
                .result();
    }
}
