package yiibai.mybatis.dao;

import yiibai.mybatis.models.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author yiibai.com
 */
public interface IUser {
    @Select("select * from user where id= #{id}")
    public User getUserByID(int id);
}