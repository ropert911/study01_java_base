package com.study.mqtt.server.emqx;

/**
 * emqx web address
 *
 * @author sk-qianxiao
 * @date 2020/3/17
 */
public class EmqxUrlFormat {
    public static String EMQX_SUBSCRIPTION_QUERY_FORMAT = "http://%s/api/v3/subscriptions";
    public static String EMQX_CONNECTION_QUERY_FORMAT = "http://%s/api/v3/connections";
    public static String EMQX_CONNECTION_PAGE_QUERY_FORMAT = "http://%s/api/v3/connections?_page={page}&_limit={limit}";
}
