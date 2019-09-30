package com.yiibai.maper;

import java.util.List;

import com.yiibai.pojo.Order;
import com.yiibai.pojo.User;

public interface UserMaper { 
	public User getUserById(int userId);
	public List<Order> getUserOrders(int userId);
}
