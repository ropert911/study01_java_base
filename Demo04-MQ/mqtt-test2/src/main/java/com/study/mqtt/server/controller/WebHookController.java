package com.study.mqtt.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.mqtt.server.constant.WebHookConstant;
import com.study.mqtt.server.mqtt.MqttService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sk-qianxiao
 * @date 2020/3/17
 */
@Api(value = "emqx webook")
@RestController
@Slf4j
public class WebHookController {
    @Autowired
    MqttService mqttService;

    @ApiOperation(value = "emqx call back")
    @PostMapping("/webHook")
    public void onWebHook(@RequestBody String message) {
        JSONObject configJson = JSONObject.parseObject(message);
        String action = configJson.getString(WebHookConstant.ACTION);
        switch (action) {
            //断开的通知
            case WebHookConstant.ACTION_DISCONNECTED: {
                //client id
                String client_id = configJson.getString(WebHookConstant.CLINET_ID);

            }
            break;
            //订阅的通知
            case WebHookConstant.ACTION_SUBSCRIBE: {
                String client_id = configJson.getString(WebHookConstant.CLINET_ID);
                String topic = configJson.getString(WebHookConstant.TOPIC);
            }
            break;
            default:
                break;
        }

        mqttService.publishConfig("clientId", "content");
    }
}
