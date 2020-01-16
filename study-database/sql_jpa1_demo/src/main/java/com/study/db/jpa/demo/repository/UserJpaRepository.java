package com.study.db.jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.db.jpa.demo.domain.User;


//JpaRepository 继承Repository， 添加很多自定义的方法
public interface UserJpaRepository extends JpaRepository<User,Long> {

}
