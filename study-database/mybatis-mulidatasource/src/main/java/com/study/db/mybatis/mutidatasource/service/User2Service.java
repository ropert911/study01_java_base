package com.study.db.mybatis.mutidatasource.service;

import com.study.db.mybatis.mutidatasource.entity.UserEntity;
import com.study.db.mybatis.mutidatasource.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sk-qianxiao
 */
@Service
public class User2Service {

	@Autowired
	private User2Mapper userMapper;

	public List<UserEntity> getUsers() {
		List<UserEntity> users=userMapper.getAll();
		return users;
	}

	public UserEntity getUser(Long id) {
		UserEntity user=userMapper.getOne(id);
		return user;
	}

	public void save(UserEntity user) {
		userMapper.insert(user);
	}

	public void update(UserEntity user) {
		userMapper.update(user);
	}

	public void delete(Long id) {
		userMapper.delete(id);
	}
}