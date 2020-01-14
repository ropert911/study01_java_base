package com.study.db.mybatis.mutidatasource.dao;

import com.study.db.mybatis.mutidatasource.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}