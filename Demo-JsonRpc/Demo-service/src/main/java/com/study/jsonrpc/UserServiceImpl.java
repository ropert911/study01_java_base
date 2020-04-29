package com.study.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Date;

@AutoJsonRpcServiceImpl
@Component
public class UserServiceImpl implements UserService {

    public User getUserByUsername(String userName) {
        if(userName == null)
            return null;
        return newUser();
    }

    public User test() {
        return newUser();
    }

    private User newUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("zhaoshg");
        user.setNickName("中文名");
        user.setLastLoginTime(new Date());
        return user;
    }

    public void saveUser(User user) {
        System.out.println(user.toString());
    }
}
