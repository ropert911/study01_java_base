package com.xq.study.other.model;

import java.util.Optional;

/**
 * @author sk-qianxiao
 */
public class Person {
    private Optional<Car> car;

    public Person(Optional<Car> car) {
        this.car = car;
    }

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
