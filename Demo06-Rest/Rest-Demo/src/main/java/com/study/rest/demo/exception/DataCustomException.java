package com.study.rest.demo.exception;



/**
 * 数据定制业务异常统一处理
 */
public class DataCustomException extends Exception {
    public DataCustomException(String message){
        super("DataCustomException:"+message);
    }

}
