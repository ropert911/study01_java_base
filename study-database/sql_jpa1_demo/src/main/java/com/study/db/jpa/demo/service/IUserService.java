package com.study.db.jpa.demo.service;

import java.util.List;

import com.study.db.jpa.demo.domain.User;

public interface IUserService {
    List<User> findAll();

    void saveUser(User book);

    User findOne(long id);

    void delete(long id);

    List<User> findByName(String name);
}
