package com.xq.study.mongo.dao;


import com.xq.study.mongo.entity.User;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao {

    void saveUser(User user);

    User findUserByUserName(String userName);

    int updateUser(User user);

    void deleteUserById(Long id);
}
