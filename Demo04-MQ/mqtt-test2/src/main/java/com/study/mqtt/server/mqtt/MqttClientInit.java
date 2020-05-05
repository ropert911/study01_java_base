package com.study.mqtt.server.mqtt;

import com.study.mqtt.server.emqx.MqttUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * MqttClient 初始化
 *
 * @author sk-shifanwen
 * @date 2019/8/14
 */
@Component
@Slf4j
public class MqttClientInit {
    @Value("${cm.mqtt.maxInflight}")
    private int maxInflight;

    @Value("${cm.mqtt.keepAliveInterval}")
    private int keepAliveInterval;

    @Value("${cm.mqtt.maxReconnectDelay}")
    private int maxReconnectDelay;

    @Value("${cm.mqtt.cleanSession}")
    private boolean cleanSession;

    /**
     * 就否相信所有的证书
     */
    @Value("${cm.mqtt.trustall}")
    private boolean trustAll;

    @Value("${cm.mqtt.capath}")
    private String caPath;

    /**
     * 初始化MqttClient， 初始化失败自动重试，直到初始化成功为止
     */
    public MqttClient initMqttClient(String broker, String username, String password, String clientId, boolean usessl) {
        MqttClient mqttClient = getMqttClient(broker, username, password, clientId, usessl);
        while (null == mqttClient) {
            mqttClient = getMqttClient(broker, username, password, clientId, usessl);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
        }
        return mqttClient;
    }

    /**
     * 初始化MqttClient
     */
    public MqttClient getMqttClient(String broker, String username, String password, String clientId, boolean usessl) {
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            if (StringUtils.isBlank(clientId)) {
                clientId = MqttAsyncClient.generateClientId();
            }

            MqttConnectOptions connOpts = new MqttConnectOptions();
            String[] brokers = broker.split(",");
            if (brokers.length > 1) {
                connOpts.setServerURIs(brokers);
                broker = brokers[0];
            }
            connOpts.setMaxInflight(maxInflight);
            connOpts.setKeepAliveInterval(keepAliveInterval);
            connOpts.setMaxReconnectDelay(maxReconnectDelay);
            connOpts.setCleanSession(cleanSession);
            connOpts.setAutomaticReconnect(true);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());

            if (usessl) {
                connOpts.setSocketFactory(MqttUtil.getSSLSocktet(trustAll, caPath));
                //关闭主机名检验
                connOpts.setHttpsHostnameVerificationEnabled(false);
            }

            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            log.info("Connecting to brokers:" + Arrays.asList(brokers));
            sampleClient.connect(connOpts);
            log.info("Connected to broker:" + sampleClient.getCurrentServerURI());
            return sampleClient;
        } catch (MqttException me) {
            log.error("reason: " + me.getReasonCode());
            log.error("msg: " + me.getMessage());
            log.error("loc: " + me.getLocalizedMessage());
            log.error("cause: " + me.getCause());
            log.error("exception: " + me);
        }
        return null;
    }
}
