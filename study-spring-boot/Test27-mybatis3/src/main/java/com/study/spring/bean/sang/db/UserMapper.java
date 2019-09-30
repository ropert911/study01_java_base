package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.User;

/**
 * Created by sang on 17-1-13.
 */
public interface UserMapper {
    public User getUser(Long id);

    public int insertUser(User user);

    public int deleteUser(Long id);
}
