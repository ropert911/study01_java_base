package com.study.db.mybatis1.mapper;


import com.study.db.mybatis1.models.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sk-qianxiao
 */
public interface IUser {
    @Select("select * from user where id= #{id}")
    User getUserById(int id);

    User getUser(int id);

    List<User> getUserList();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);
}