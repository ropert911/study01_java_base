package com.study.db.jpa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.study.db.jpa.demo.domain.User;

public interface UserRepository extends Repository<User, Long> {
    List<User> findByNameAndAddress(String name, String address);

    @Query(value = "from User u where u.name=:name")
    List<User> findByName1(@Param("name") String name);

    //本地化语句执行  修改原因 #{#entityName}得到的是大写的demo.User,  应该是user
//    @Query(value = "select * from #{#entityName} u where u.name=?1", nativeQuery = true)
    @Query(value = "select * from demo.user u where u.name=?1", nativeQuery = true)
    List<User> findByName2(String name);

    List<User> findByName(String name);
}
