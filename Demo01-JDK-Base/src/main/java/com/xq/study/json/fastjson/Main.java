package com.xq.study.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xq.study.json.fastjson.model.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) throws Exception{
        MyUser a = new MyUser();
        a.setName("xq1");
        MyUser b = new MyUser();
        b.setName("xq2");
        List<MyUser> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        String valueUser = JSON.toJSONString(a);
        System.out.println("获取结果如下");
        System.out.println(valueUser);
        System.out.println("============11111");

        String valueUserList = JSON.toJSONString(list);
        System.out.println("获取list结果如下");
        System.out.println(valueUserList);
        System.out.println("============2222");


        JSONObject configJson = JSON.parseObject(valueUser);
        String name = configJson.getString("name");
        System.out.println("获取单个用户的数据");
        System.out.println(name);
        System.out.println("====================3333333");

        JSONObject configJson2 = JSONObject.parseObject(valueUser);
        String name2 = configJson2.getString("name");
        System.out.println("JSONObject获取单个用户的数据");
        System.out.println(name2);
        for (Map.Entry<String, Object> entry : configJson2.entrySet()) {
            System.out.println("Key:" + entry.getKey() + " Value:" + entry.getValue().toString());
        }
        System.out.println("====================444444444");
    }
}
