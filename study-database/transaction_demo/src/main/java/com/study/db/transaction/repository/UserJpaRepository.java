package com.study.db.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.db.transaction.domain.User;


public interface UserJpaRepository extends JpaRepository<User,Long> {

}
