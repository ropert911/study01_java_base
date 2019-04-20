package com.xq.study.webservice.webserver;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by sk-qianxiao on 2019/4/18.
 */
@WebService
public class SayWeb {

    public String sayHello(String name) {
        return "hello" + name;
    }

    public void sayBye() {
        System.out.println("bye");
    }

    //此方法不会被发布
    @WebMethod(exclude = true)
    public int sayInt(int i) {
        return ++i;
    }

    //静态方法不会被发布
    public static void sayGood() {
        System.out.println("Good");
    }

}