package com.study.rest.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 定制异常处理统一返回格式
 * Created by sk-shifanwen on 2017/6/30.
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(DataCustomException.class)
    public String exceptionHandler(DataCustomException ex, HttpServletRequest req){
        return "CustomExceptionHandler捕获：" + ex.getMessage();
    }

}
