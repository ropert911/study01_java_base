package com.study.db.mybatis.mutidatasource.service;

import com.study.db.mybatis.mutidatasource.entity.UserEntity;
import com.study.db.mybatis.mutidatasource.mapper.test1.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sk-qianxiao
 */
@Service
public class User1Service {

	@Autowired
	private User1Mapper user1Mapper;

	public List<UserEntity> getUsers() {
		List<UserEntity> users= user1Mapper.getAll();
		return users;
	}

	public UserEntity getUser(Long id) {
		UserEntity user= user1Mapper.getOne(id);
		return user;
	}

	public void save(UserEntity user) {
		user1Mapper.insert(user);
	}

	public void update(UserEntity user) {
		user1Mapper.update(user);
	}

	public void delete(Long id) {
		user1Mapper.delete(id);
	}
}