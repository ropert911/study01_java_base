package com.study.db.mybatis.flyway.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 配置信息数据库保存结构
 *
 * @author sk-qianxiao
 * @date 2020/4/17
 */
@Data
public class ConfigInfo {
    /**
     * 配置ID
     */
    private String configId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 配置创建时间
     */
    private Timestamp createTime;
    /**
     * 配置下发时间
     */
    private Timestamp configTime;
    /**
     * 配置状态
     */
    private int configStatus;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 持久化的配置信息
     */
    private String configInfo;
    /**
     * 当前配置状态信息
     */
    private String configResult;

    public void  updateStatus(int configStatus, String configResult){
        this.configStatus = configStatus;
        this.configResult = configResult;
    }
}
