package com.study.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.transaction.domain.User;


public interface UserJpaRepository extends JpaRepository<User,Long> {

}
