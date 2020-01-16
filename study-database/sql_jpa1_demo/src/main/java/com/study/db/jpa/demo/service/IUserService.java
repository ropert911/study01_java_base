package com.study.db.jpa.demo.service;

import java.util.List;

import com.study.db.jpa.demo.domain.User;

public interface IUserService
{
    public List<User> findAll();

    public void saveUser(User book);

   
    public User findOne(long id);

    public void delete(long id);

    public List<User> findByName(String name);

}
