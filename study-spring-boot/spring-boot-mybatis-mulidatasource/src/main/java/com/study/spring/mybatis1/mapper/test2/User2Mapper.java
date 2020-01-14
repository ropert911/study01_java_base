package com.study.spring.mybatis1.mapper.test2;

import java.util.List;

import com.study.spring.mybatis1.entity.UserEntity;

public interface User2Mapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}