package com.study.base.网络_okhttp客户端;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import okio.BufferedSink;

import java.io.IOException;

public class Example2SyncPost {
    public static void main(String[] args) throws IOException {
        postJson();
        postFormBody();
        postStringBody();
        postMediaType();
    }

    public static String postJson() throws IOException {
        String url = "http://192.168.80.10:8001/company/clean";
        JSONObject json = new JSONObject();
        json.put("value", "value");
        json.put("label", 0);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public static void postFormBody() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormEncodingBuilder().add("query", "Hello").build();
        Request request = new Request.Builder().url("http://www.baidu.com").post(formBody).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());
    }

    public static void postStringBody() throws IOException {
        OkHttpClient client = new OkHttpClient();

        final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        final String postBody = "Hello World";
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_TEXT;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8(postBody);
            }

            @Override
            public long contentLength() throws IOException {
                return postBody.length();
            }
        };

        Request request = new Request.Builder().url("http://www.baidu.com").post(requestBody).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.body().string());
    }

    public static void postMediaType() throws IOException {
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
