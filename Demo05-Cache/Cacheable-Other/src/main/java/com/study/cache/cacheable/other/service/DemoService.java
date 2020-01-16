package com.study.cache.cacheable.other.service;

import com.study.cache.cacheable.other.dao.Person;

/**
 * @author sk-qianxiao
 */
public interface DemoService {
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);

}
