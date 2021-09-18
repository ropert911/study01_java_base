package com.study.base.网络_okhttp客户端;

import com.squareup.okhttp.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 请求结果进行缓存
 */
public class Example6CacheResponse {
    public static void main(String[] args) throws IOException {
        int cacheSize = 100 * 1024 * 1024;

        OkHttpClient client = new OkHttpClient();

        //设置缓存
        File cacheDirectory = Files.createTempDirectory("cache").toFile();
        client.setCache(new Cache(cacheDirectory, cacheSize));

        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.cacheResponse());
        System.out.println(response.networkResponse());
    }
}
