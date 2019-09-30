package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.User;

/**
 * @author sk-qianxiao
 * Created by sang on 17-1-13.
 */
public interface UserMapper {
    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(Long id);
}
