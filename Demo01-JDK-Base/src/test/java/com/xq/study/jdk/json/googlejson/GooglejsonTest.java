package com.xq.study.jdk.json.googlejson;

import com.google.gson.Gson;
import com.xq.study.model.Family;
import org.junit.Test;

public class GooglejsonTest {
    @Test
    public void test1() {
        Family a = new Family();
        a.setfName("xqFamily");

        Gson gson = new Gson();
        String str = gson.toJson(a);
        System.out.println(str);

        Family b = gson.fromJson(str, Family.class);
        System.out.println(b);  //调用toString函数
    }
}
