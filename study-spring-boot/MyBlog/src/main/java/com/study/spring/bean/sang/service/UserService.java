package com.study.spring.bean.sang.service;

import com.study.spring.bean.sang.dao.UserDao;
import com.study.spring.bean.sang.entity.User;
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
