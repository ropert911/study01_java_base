package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.Person;
import com.study.spring.bean.sang.bean.User;

import java.util.List;

/**
 * Created by sang on 17-1-13.
 */
public interface UserMapper {
    public List<User> getUser();
    public List<User> getUser2();
    public List<Person> getPerson();

    public int insertUser(User user);
    public int insertUser2(User user);
    public int insertUser3(User user);
    public int insertUser4(User user);
    public int insertPerson(Person p);
}
