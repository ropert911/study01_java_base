package com.study.mqtt.server.emqx;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class EmqxSubResponseData {
    private String client_id;
    private String node;
    private int qos;
    private String topic;
}