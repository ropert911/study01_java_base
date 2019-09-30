package yiibai.mybatis.dao;

import yiibai.mybatis.models.User;

import java.util.List;

/**
 * @author yiibai
 */
public interface IUser {
    //@Select("select * from user where id= #{id}")
    //public User getUserByID(int id);
    List<User> getUserList();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

    User getUser(int id);
}
