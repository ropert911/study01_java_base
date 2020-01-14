package com.study.spring.mybatis1.mapper;

import java.util.List;

import com.study.spring.mybatis1.entity.UserEntity;

public interface UserMapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}