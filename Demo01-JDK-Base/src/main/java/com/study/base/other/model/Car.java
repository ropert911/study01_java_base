package com.study.base.other.model;

import java.util.Optional;

/**
 * @author sk-qianxiao
 */
public class Car {
    private Optional<Insurance> insurance;

    public Car(Optional<Insurance> insurance){
        this.insurance = insurance;
    }

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
