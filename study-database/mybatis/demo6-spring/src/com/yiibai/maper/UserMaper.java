package com.yiibai.maper;

import java.util.List;

import yiibai.pojo.Order;
import yiibai.pojo.User;

public interface UserMaper { 
	public User getUserById(int userId);
	public List<Order> getUserOrders(int userId);
}
