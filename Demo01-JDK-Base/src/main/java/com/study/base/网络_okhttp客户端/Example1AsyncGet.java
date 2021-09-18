package com.study.base.网络_okhttp客户端;


import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * 异步请求
 */
public class Example1AsyncGet {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://www.baidu.com").build();
        client.newCall(request).enqueue(httpCallBack());

        System.out.println("this is end of main");
    }

    public static Callback httpCallBack() {
        return new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }

                System.out.println("inner callback");
                System.out.println(response.body().string());
            }
        };
    }
}
