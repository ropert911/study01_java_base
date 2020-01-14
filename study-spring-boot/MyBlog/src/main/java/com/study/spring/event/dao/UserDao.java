package com.study.spring.event.dao;

import com.study.spring.event.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by sang on 17-3-10.
 */
@Repository
public interface UserDao {
    public User getUser(@Param("username") String username, @Param("password") String password);
}
