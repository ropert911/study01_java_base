package com.xq.study.rest.Controller;

import com.xq.study.rest.dto.User;
import com.xq.study.rest.exception.DataCustomException;
import com.xq.study.rest.exception.MyException;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by xq on 2017/9/20.
 */
@RestController
@RequestMapping("/xq")
public class HelloController extends BaseController{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping(method = RequestMethod.GET)
    public String say() {
        return "Hello, this is xq.";
    }
    @RequestMapping("/name")
    public String name() {
        return "name:xiaoqian";
    }

    //返回Jason格式的对象
    @RequestMapping(path="/users", method = RequestMethod.GET)
    public User getUser() {
        User user=new User();
        user.setUserName("小明");
        user.setPassWord("xxx2");
        return user;
    }

    @RequestMapping("/exception1")
    public String exception1() throws MyException{
        throw new MyException("MyException 进行捕获");
    }
    @RequestMapping("/exception2")
    public String exception2() throws DataCustomException{
        throw new DataCustomException("ControllerAdvice 进行捕获");
    }

    @RequestMapping("/locale")
    public Locale hello(Locale locale, Model model) {
        return locale;
    }

    @RequestMapping("/model")
    public String model(Locale locale, Model model) {
        model.addAttribute("greeting", "Hello!");

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "hello";
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
