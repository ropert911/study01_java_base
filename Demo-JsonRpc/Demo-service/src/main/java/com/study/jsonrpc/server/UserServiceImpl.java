package com.study.jsonrpc.server;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.study.jsonrpc.User;
import com.study.jsonrpc.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;

@AutoJsonRpcServiceImpl
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUsername(String userName) {
        if (userName == null) {
            return null;
        }
        return newUser();
    }

    @Override
    public User test() {
        return newUser();
    }

    @Override
    public void saveUser(User user) {
        System.out.println(user.toString());
    }

    private User newUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("zhaoshg");
        user.setNickName("中文名");
        user.setLastLoginTime(new Date());
        return user;
    }
}
