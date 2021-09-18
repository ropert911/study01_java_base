package com.study.base.网络_okhttp客户端;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

public class Example4UrlEncoder {

    public static String get(String url, HashMap<String, String> paramsMap) throws IOException {
        StringBuilder tempParams = new StringBuilder();

        OkHttpClient client = new OkHttpClient();
        //处理参数
        int pos = 0;
        for (String key : paramsMap.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            //对参数进行URLEncoder
            tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
            pos++;
        }

        //补全请求地址
        String requestUrl = String.format("%s?%s", url, tempParams.toString());
        System.out.println("请求url：" + requestUrl);

        Request request = new Request.Builder().url(requestUrl).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static void main(String[] args) throws IOException {
        HashMap map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");

        String content = get("http://www.baidu.com", map);
        System.out.println(content);
    }

}