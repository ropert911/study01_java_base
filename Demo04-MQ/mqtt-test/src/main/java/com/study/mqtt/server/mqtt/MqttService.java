package com.study.mqtt.server.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.study.mqtt.server.emqx.EmqxRestClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Set;


@EnableScheduling
@Service
@Slf4j
public class MqttService {
    @Value("${cm.mqtt.url}")
    private String brokerTCP;

    @Value("${cm.mqtt.url.ssl}")
    private String brokerSSL;

    @Value("${cm.mqtt.usessl}")
    private boolean usessl;

    @Value("${cm.mqtt.qos}")
    private int defaultQos;

    @Value("${cm.mqtt.username}")
    private String username;

    @Value("${cm.mqtt.password}")
    private String password;

    @Value("${cm.mqtt.inbound.topic}")
    private String inDcTopic;

    @Value("${cm.mqtt.outbound.topic.prefix}")
    private String outTopicPre;

    @Value("${cm.mqtt.clientid}")
    private String mqttClientId;

    @Value("${cm.mqtt.emq.restapi.address}")
    private String emqRestAddress;


    private MqttClient mqttClient;
    private final MqttClientInit mqttClientInit;
    private final RestTemplate restTemplate;

    @Autowired
    public MqttService(MqttClientInit mqttClientInit, @Qualifier("mqttSubRestTemplate") RestTemplate restTemplate) {
        this.mqttClientInit = mqttClientInit;
        this.restTemplate = restTemplate;
    }

    /**
     * mqtt消息订阅服务初始化
     */
    @PostConstruct
    private void mqttSubInit() {
        while (!subscribe(inDcTopic, defaultQos)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * 订阅指定topic，并定义qos
     */
    public boolean subscribe(String topic, int qos) {
        log.info("listen mqtt ======================{}", topic);
        try {
            log.info("initSub start ...");
            mqttClient().subscribeWithResponse(topic, qos).waitForCompletion(3000);
        } catch (MqttException e) {
            log.warn("initSub error!", e);
            mqttClient = null;
            return false;
        }
        mqttClient().setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverUri) {
                log.info("connectComplete [" + serverUri + "] reconnect [" + reconnect + "]");
                try {
                    Set<String> clientIdList = EmqxRestClient.getClientIdFromEmqxSubscription(restTemplate, emqRestAddress, mqttClientId);
                    if (CollectionUtils.isEmpty(clientIdList) || !clientIdList.contains(mqttClientId)) {
                        log.info("reSubscribe start ...");
                        mqttClient().subscribeWithResponse(topic, qos).waitForCompletion(3000);
                        log.info("reSubscribe success.");
                    }
                } catch (Exception e) {
                    log.warn("reSubscribe failed!", e);
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                log.info("message arrived, topic: {}  msg: {}", topic, message);
                String itfId = topic.substring(topic.lastIndexOf("/") + 1);

                try {
                    JSONObject configJson = JSONObject.parseObject(message.toString());
                    String serverName = configJson.getString("serverName");
                    String clientId = configJson.getString("clientId");
                    String configId = configJson.getString("configId");
                    int code = configJson.getInteger("code");
                    String configMsg = configJson.getString("message");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info(e.getMessage());
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
                log.warn("MqttSub connectionLost!", throwable);
            }
        });
        log.info("initSub finished.");
        return true;
    }

    public void publishConfig(String clientId, String content) {
        try {
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(defaultQos);
            log.info("mqtt send topic={}  message={}", outTopicPre + clientId, content);
            mqttClient().publish(outTopicPre + clientId, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public MqttClient mqttClient() {
        if (null == mqttClient) {
            log.info("MqttSub client init: " + mqttClientId);
            String broker = brokerTCP;
            if (usessl) {
                broker = brokerSSL;
            }
            mqttClient = mqttClientInit.initMqttClient(broker, username, password, mqttClientId, usessl);
        }
        return mqttClient;
    }

    /**
     * 定时检测连接状态，如果当前连接状态断开，则立即重连。
     * 虽然已经设置了自动连接，但是还是会有极端情况下导致重连没有成功，所以这个任务相当于再加一道防线
     */
    @Scheduled(fixedDelay = 90 * 1000)
    private void schedule() {
        try {
            if (null != mqttClient && !mqttClient.isConnected()) {
                log.info(" is not connected, reconnect");
                mqttClient.reconnect();
            }
        } catch (Exception e) {
            log.error("mqtt-sub-scan error!", e);
        }
    }
}
