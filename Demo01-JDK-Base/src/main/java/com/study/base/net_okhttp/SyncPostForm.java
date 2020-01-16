package com.study.base.net_okhttp;

import com.squareup.okhttp.*;

import java.io.IOException;

public class SyncPostForm {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormEncodingBuilder().add("query", "Hello").build();

        Request request = new Request.Builder().url("http://www.baidu.com").post(formBody).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());
    }
}
