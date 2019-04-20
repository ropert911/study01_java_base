package com.xq.study.jdk;

import java.util.Optional;

/**
 * Created by xq on 2019/3/1.
 */
public class OptionalTest {
    public static void main(String[] args) {
        {   //初始化
            Optional<Car> optCar = Optional.empty();            //声明空的对象
            Optional<Car> optCar1 = Optional.of(new Car());     //依据一个非空值创建Optional
            Optional<Car> optCar2 = Optional.ofNullable(null);  //可接受null的Optional

            if (optCar.isPresent()) {
                //如果有值在做操作
            }
            Car c = optCar.get();
            Car c2 = optCar.orElse(c);  //Optional对象不包含值时提供一个默认值

            Optional<Insurance> optInsurance = Optional.empty();
            optInsurance.filter(insurance -> "CambridgeInsurance".equals(insurance.getName()))  //只有
                    .ifPresent(x -> System.out.println("ok"));
        }

        {   //连接调用防止空判断
            Optional<Person> optPerson = Optional.of(null);
            String name = optPerson.flatMap(Person::getCar)
                    .flatMap(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
        }
    }
}

class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}
