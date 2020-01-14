package com.study.db.mybatis.xml.mapper;

import java.util.List;

import com.study.db.mybatis.xml.entity.UserEntity;

public interface UserMapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}