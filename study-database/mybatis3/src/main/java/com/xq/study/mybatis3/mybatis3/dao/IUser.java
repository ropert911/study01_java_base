package com.xq.study.mybatis3.mybatis3.dao;


import com.xq.study.mybatis3.mybatis3.models.User;

import java.util.List;

/**
 * @author sk-qianxiao
 */
public interface IUser {
    List<User> getUserList();
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

    User getUser(int id);
}
