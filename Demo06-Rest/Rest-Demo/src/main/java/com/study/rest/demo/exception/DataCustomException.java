package com.study.rest.demo.exception;



/**
 * 数据定制业务异常统一处理
 * Created by sk-shifanwen on 2017/6/30.
 */
public class DataCustomException extends Exception {
    public DataCustomException(String message){
        super("DataCustomException:"+message);
    }

}
