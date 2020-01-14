package com.study.spring.mybatis1.sevice.impl;

import com.study.spring.mybatis1.dao.UserInfoDao;
import com.study.spring.mybatis1.entity.UserInfo;
import com.study.spring.mybatis1.sevice.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}