package com.study.db.mybatis.mutidatasource.mapper.test1;

import com.study.db.mybatis.mutidatasource.entity.UserEntity;
import com.study.db.mybatis.mutidatasource.entity.UserSexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface User1Mapper {
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