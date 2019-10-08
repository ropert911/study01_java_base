package com.xq.study.mybatis2.mybatis2.dao;


import com.xq.study.mybatis2.mybatis2.models.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author sk-qianxiao
 */
public interface IUser {
    @Select("select * from user where id= #{id}")
    User getUserById(int id);
}