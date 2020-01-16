package com.study.db.mongo.base.dao;


import com.study.db.mongo.base.entity.User;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao {

    void saveUser(User user);

    User findUserByUserName(String userName);

    int updateUser(User user);

    void deleteUserById(Long id);
}
