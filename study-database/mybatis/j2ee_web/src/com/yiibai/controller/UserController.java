package com.yiibai.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiibai.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yiibai.maper.UserMaper;
import com.yiibai.pojo.Order;

/**
 * @describe: 读取一个用户下的所有订单
 * @author: Yiibai
 * @version: V1.0
 * @copyright http://www.yiibai.com
 */

// http://localhost:8080/mybatis07-spring-mvc/user/orders
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMaper userMaper;

    @RequestMapping("/orders")
    public ModelAndView listall(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = userMaper.getUserOrders(1);
        System.out.println("orders");
        ModelAndView mav = new ModelAndView("user_orders");
        mav.addObject("orders", orders);
        return mav;
    }
}
