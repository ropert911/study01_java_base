package com.xq.study.mybatis1.mybatis1.dao;


import com.xq.study.mybatis1.mybatis1.models.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author sk-qianxiao
 */
public interface IUser {
    @Select("select * from user where id= #{id}")
    User getUserById(int id);
}