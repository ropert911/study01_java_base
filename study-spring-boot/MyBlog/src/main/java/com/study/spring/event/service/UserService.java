package com.study.spring.event.service;

import com.study.spring.event.dao.UserDao;
import com.study.spring.event.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sang on 17-3-10.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public boolean login(String username, String password) {
        User user = userDao.getUser(username, password);
        if (user == null) {
            return false;
        }else{
            return true;
        }
    }
}
