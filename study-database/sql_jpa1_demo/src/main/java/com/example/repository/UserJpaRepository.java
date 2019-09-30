package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;


//JpaRepository 继承Repository， 添加很多自定义的方法
public interface UserJpaRepository extends JpaRepository<User,Long> {

}
