package com.study.base.网络_okhttp客户端;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.Arrays;
import java.util.List;

/**
 * 请求拦截器
 */
public class Example8Interceptor {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        //LogginInterceptor 请求时进行拦截
        client.interceptors().add(new RequestInterceptor());

        String[] split = {"b647a889-8962-11e7-b8af-70106fb01274"};
        List<String> errIds = Arrays.asList(split);
        for (String item : errIds) {
            try {
                Request request = new Request.Builder().url("http://www.baidu.com?" + item).build();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    System.out.println(item + ": 服务器端错误: " + response);
                    continue;
                }

                System.out.println(response.body().string());
                System.out.println("================================================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
