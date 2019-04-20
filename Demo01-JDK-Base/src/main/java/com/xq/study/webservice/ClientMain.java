package com.xq.study.webservice;

import com.xq.study.webservice.webclient.SayWeb;
import com.xq.study.webservice.webclient.SayWebService;

/**
 * Created by sk-qianxiao on 2019/4/19.
 */
public class ClientMain {
    public static void main(String[] args) {
        SayWeb serviceTest = new SayWebService().getSayWebPort();//初始化对象
        String name = serviceTest.sayHello("xiaoming");//调用服务端方法
        serviceTest.sayBye();

        System.out.println(name);//打印返回结果
    }
}
