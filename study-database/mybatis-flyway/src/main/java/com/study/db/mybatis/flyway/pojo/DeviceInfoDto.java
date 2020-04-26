package com.study.db.mybatis.flyway.pojo;

import lombok.Data;

/**
 * 设备信息
 *
 * @author sk-qianxiao
 * @date 2020/4/20
 */
@Data
public class DeviceInfoDto {
    /**
     * 设备MAC
     */
    private String mac;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备版本
     */
    private String version;
    /**
     * 设备IP
     */
    private String ip;
    /**
     * 活跃状态
     */
    private Boolean active;
}
