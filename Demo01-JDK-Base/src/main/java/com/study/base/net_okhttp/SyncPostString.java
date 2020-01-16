package com.study.base.net_okhttp;

import com.squareup.okhttp.*;

import java.io.IOException;

public class SyncPostString {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        RequestBody stringbody = RequestBody.create(MEDIA_TYPE_TEXT, "Hello World");

        Request request = new Request.Builder().url("http://www.baidu.com").post(stringbody).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());
    }
}
