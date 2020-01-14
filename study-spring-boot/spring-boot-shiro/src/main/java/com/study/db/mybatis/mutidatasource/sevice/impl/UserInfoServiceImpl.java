package com.study.db.mybatis.mutidatasource.sevice.impl;

import com.study.db.mybatis.mutidatasource.dao.UserInfoDao;
import com.study.db.mybatis.mutidatasource.entity.UserInfo;
import com.study.db.mybatis.mutidatasource.sevice.UserInfoService;
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