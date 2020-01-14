package com.study.spring.mybatis.annotation.service;

import com.study.spring.mybatis.annotation.entity.UserEntity;
import com.study.spring.mybatis.annotation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sk-qianxiao
 */
@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
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