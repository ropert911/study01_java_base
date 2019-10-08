package com.xq.study.mybatis6.mybatis6.maper;

import com.xq.study.mybatis6.mybatis6.pojo.Order;
import com.xq.study.mybatis6.mybatis6.pojo.User;

import java.util.List;

/**
 * @author sk-qianxiao
 */
public interface UserMaper {
    User getUserById(int userId);

    List<Order> getUserOrders(int userId);
}
