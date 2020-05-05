package com.study.mqtt.server.emqx;

import lombok.Data;

import java.util.List;

@Data
public class EmqxSubResponse {
    private int code;
    private List<EmqxSubResponseData> data;
}
