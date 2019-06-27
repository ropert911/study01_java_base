package com.xq.study.json.googlejson;

import com.google.gson.Gson;

public class GooglejsonTest {
    public static void main(String arg[]) {
        Family a = new Family();
        a.setfName("xqFamily");

        Gson gson = new Gson();
        String str = gson.toJson(a);
        System.out.println(str);

        Family b = gson.fromJson(str, Family.class);
        System.out.println(b);  //调用toString函数
    }
}
