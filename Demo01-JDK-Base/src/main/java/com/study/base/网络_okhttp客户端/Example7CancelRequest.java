package com.study.base.网络_okhttp客户端;

import com.google.common.collect.Lists;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * 取消请求
 */
public class Example7CancelRequest {
    private static OkHttpClient client = new OkHttpClient();
    private static String tag = "website";

    public static void sendRequests(List<String> urls) {
        for (String item : urls) {
            //进行异步请求
            Request request = new Request.Builder().url(item).tag(tag).build();
            client.newCall(request).enqueue(new SimpleCallback());
        }
    }

    private static class SimpleCallback implements Callback {
        public void onFailure(Request request, IOException e) {
            e.printStackTrace();
        }

        public void onResponse(Response response) throws IOException {
            System.out.println(response.body().string());
        }
    }

    public static void main(String[] args) throws IOException {
        sendRequests(Lists.newArrayList(
                "http://www.baidu.com",
                "http://www.163.com",
                "http://www.sina.com.cn"));
        client.cancel(tag);
    }
}
