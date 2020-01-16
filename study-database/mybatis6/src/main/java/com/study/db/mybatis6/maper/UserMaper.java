package com.study.db.mybatis6.maper;

import com.study.db.mybatis6.pojo.Order;
import com.study.db.mybatis6.pojo.User;

import java.util.List;

/**
 * @author sk-qianxiao
 */
public interface UserMaper {
    User getUserById(int userId);

    List<Order> getUserOrders(int userId);
}
