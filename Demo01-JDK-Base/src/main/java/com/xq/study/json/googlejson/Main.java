package com.xq.study.json.googlejson;

import com.google.gson.Gson;
import com.xq.study.json.googlejson.model.Family;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Family a = new Family();
        a.setfName("xqFamily");

        Gson gson = new Gson();
        String str = gson.toJson(a);
        System.out.println(str);

        Family b = gson.fromJson(str, Family.class);
        //调用toString函数
        System.out.println(b);
    }
}
