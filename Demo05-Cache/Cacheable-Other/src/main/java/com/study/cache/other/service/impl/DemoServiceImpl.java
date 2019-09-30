package com.study.cache.other.service.impl;

import com.study.cache.other.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.study.cache.other.dao.PersonRepository;
import com.study.cache.other.service.DemoService;

/**
 * @author sk-qianxiao
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id、key为" + id + "的数据缓存");
        //这里不做实际删除操作
    }

    @Override
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }

}
