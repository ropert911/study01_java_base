package com.xq.study.jdk;

import com.xq.study.model.Car;
import com.xq.study.model.Insurance;
import com.xq.study.model.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by xq on 2019/3/1.
 */
public class OptionalTest {
    @Test
    public void test1() {
        Optional<Car> optCarNull = Optional.empty();            //声明空的对象
        Optional<Car> optCar1 = Optional.ofNullable(null);  //可接受null的Optional
        Optional<Insurance> insurance = Optional.of(new Insurance("中国人寿"));
        Optional<Car> optCar2 = Optional.of(new Car(insurance));     //依据一个非空值创建Optional
        Optional<Person> optPerson = Optional.of(new Person(optCar2));


        if (optCar2.isPresent()) {
            System.out.println("111111=" + optCar2.get());
        }
        /**Optional对象不包含值时提供一个默认值*/
        Car c2 = optCar1.orElse(optCar2.get());


        c2.getInsurance().filter(ins -> "中国人寿".equals(ins.getName()))
                .ifPresent(x -> System.out.println("ok"));

        String name = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
        System.out.println(name);

        name = optCar1.flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
        System.out.println(name);
    }
}
