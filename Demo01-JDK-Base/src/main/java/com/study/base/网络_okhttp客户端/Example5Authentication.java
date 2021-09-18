package com.study.base.网络_okhttp客户端;


import com.squareup.okhttp.*;

import java.io.IOException;
import java.net.Proxy;

/**
 * client登陆时进行认证
 */
public class Example5Authentication {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        client.setAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String credential = Credentials.basic("user", "password");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });

        Request request = new Request.Builder().url("http://www.baidu.com").build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());
    }
}
