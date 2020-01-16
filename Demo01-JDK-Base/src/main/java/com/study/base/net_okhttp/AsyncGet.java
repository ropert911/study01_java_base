package com.study.base.net_okhttp;


import com.squareup.okhttp.*;

import java.io.IOException;

public class AsyncGet {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://www.baidu.com").build();

        client.newCall(request).enqueue(new Callback() {

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
        });

        System.out.println("this is end of main");
    }
}
