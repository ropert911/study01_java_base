package com.study.mqtt.server.emqx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmqxRestClient {
    private static Logger log = LoggerFactory.getLogger(EmqxRestClient.class);
    private static int pageLimit = 1000;

    private void EmqxRestClient() {
    }

    /**
     * 从emqx查询监听是否有指定的clientId
     */
    public static Set<String> getClientIdFromEmqxSubscription(RestTemplate restTemplate, String address, String clientId) {
        Set<String> clientIdList = null;
        try {
            String emqSubGetUrl = String.format(EmqxUrlFormat.EMQX_SUBSCRIPTION_QUERY_FORMAT, address) + "/" + clientId;
            log.debug("request url: " + emqSubGetUrl);
            EmqxSubResponse emqxSubResponse = restTemplate.getForObject(emqSubGetUrl, EmqxSubResponse.class);
            log.debug("response data: " + JSON.toJSONString(emqxSubResponse));
            if (emqxSubResponse != null) {
                clientIdList = emqxSubResponse.getData().stream().map(EmqxSubResponseData::getClient_id).collect(Collectors.toSet());
            }
        } catch (Exception e) {
            log.error("getClientIdFromEmqx error!", e);
        }
        return clientIdList;
    }

    /**
     * 从监听列表中获取所有指定监听client信息
     */
    public static void getConInfosFromEmqxSubscription(RestTemplate restTemplate, String address) {
        try {
            String emqConGetUrl = String.format(EmqxUrlFormat.EMQX_SUBSCRIPTION_QUERY_FORMAT, address);
            log.debug("request url: " + emqConGetUrl);
            int curPage = 1;
            int count = 0;
            boolean loopPage = true;
            while (loopPage) {
                Map<String, Object> params = new HashMap<>();
                params.put("page", String.valueOf(curPage));
                params.put("limit", String.valueOf(pageLimit));
                String result = restTemplate.getForObject(emqConGetUrl, String.class, params);
                JSONObject jsonObject = JSON.parseObject(result);
                int code = jsonObject.getInteger("code");
                if (0 == code) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.size(); i++) {
                        String topic = dataArray.getJSONObject(i).getString("topic");
                    }

                    JSONObject metaObject = jsonObject.getJSONObject("meta");
                    if (1 == curPage) {
                        count = metaObject.getInteger("count");
                    }
                }

                if (curPage * pageLimit >= count) {
                    loopPage = false;
                }
                curPage++;
            }
        } catch (Exception e) {
            log.error("getClientIdFromEmqx error!", e);
        }
    }


    /**
     * 从emqx查询连接是否有指定的clientId
     */
    public static Set<String> getClientIdFromEmqxConnection(RestTemplate restTemplate, String address, String clientId) {
        Set<String> clientIdList = new HashSet<>(1);
        try {
            String emqConGetUrl = String.format(EmqxUrlFormat.EMQX_CONNECTION_QUERY_FORMAT, address) + "/" + clientId;
            log.debug("request url: " + emqConGetUrl);
            String result = restTemplate.getForObject(emqConGetUrl, String.class);
            JSONObject jsonObject = JSON.parseObject(result);
            int code = jsonObject.getInteger("code");
            if (0 == code) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.size(); i++) {
                    String cId = dataArray.getJSONObject(i).getString("client_id");
                    clientIdList.add(cId);
                }
            }
        } catch (Exception e) {
            log.error("getClientIdFromEmqx error!", e);
        }
        return clientIdList;
    }


    /**
     * 从emqx查询所有在线的client
     */
    public static Set<String> getClientIdsFromEmqxConnection(RestTemplate restTemplate, String address) {
        Set<String> clientIdSet = new HashSet<>(1);
        try {
            String emqConGetUrl = String.format(EmqxUrlFormat.EMQX_CONNECTION_PAGE_QUERY_FORMAT, address);
            log.debug("request url: " + emqConGetUrl);
            int curPage = 1;
            int count = 0;
            boolean loopPage = true;
            while (loopPage) {
                Map<String, Object> params = new HashMap<>();
                params.put("page", String.valueOf(curPage));
                params.put("limit", String.valueOf(pageLimit));
                String result = restTemplate.getForObject(emqConGetUrl, String.class, params);
                JSONObject jsonObject = JSON.parseObject(result);
                int code = jsonObject.getInteger("code");
                if (0 == code) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.size(); i++) {
                        String cId = dataArray.getJSONObject(i).getString("client_id");
                        clientIdSet.add(cId);
                    }

                    JSONObject metaObject = jsonObject.getJSONObject("meta");
                    if (1 == curPage) {
                        count = metaObject.getInteger("count");
                    }
                }

                if (curPage * pageLimit >= count) {
                    loopPage = false;
                }
                curPage++;
            }
        } catch (Exception e) {
            log.error("getClientIdFromEmqx error!", e);
        }
        return clientIdSet;
    }
}
