package com.study.rest.demo.Controller;

import com.study.rest.demo.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	@ExceptionHandler(MyException.class)
	public String handleException(Exception ex, HttpServletRequest request) {
		return "BaseController 捕获："+ex.getMessage();
	}
}
