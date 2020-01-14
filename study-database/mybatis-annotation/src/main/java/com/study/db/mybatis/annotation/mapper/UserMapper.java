package com.study.db.mybatis.annotation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.study.db.mybatis.annotation.entity.UserEntity;
import com.study.db.mybatis.annotation.entity.UserSexEnum;

public interface UserMapper {
    @Insert("INSERT INTO users(id,userName,passWord,nick_name,user_sex) VALUES(#{id},#{userName}, #{passWord},#{nickName}, #{userSex})" +
            " ON DUPLICATE KEY UPDATE  userName=VALUES(userName),passWord=VALUES(passWord),user_sex=VALUES(user_sex),nick_name=VALUES(nick_name)")
    void insert(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(UserEntity user);


    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "passWord", column = "passWord"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class)
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "passWord", column = "passWord"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class)
    })
    UserEntity getOne(Long id);
}