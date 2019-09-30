package com.study.cache.other.service;

import com.study.cache.other.dao.Person;

/**
 * @author sk-qianxiao
 */
public interface DemoService {
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);

}
