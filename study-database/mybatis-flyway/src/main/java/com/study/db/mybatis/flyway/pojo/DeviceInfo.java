package com.study.db.mybatis.flyway.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 保存设备基本信息
 *
 * @author sk-qianxiao
 * @date 2020/4/20
 */
@Data
public class DeviceInfo {
    /**
     * 查询出来符合条件的记录个数
     */
    private int count;
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
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public DeviceInfoDto convertToDeviceInfoDto(Timestamp timeoutTimestamp) {
        DeviceInfoDto deviceInfoDto = convertToDeviceInfoDto();
        deviceInfoDto.setActive(checkActive(timeoutTimestamp));
        return deviceInfoDto;
    }

    public DeviceInfoDto convertToDeviceInfoDto() {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setMac(mac);
        deviceInfoDto.setModel(model);
        deviceInfoDto.setVersion(version);
        deviceInfoDto.setIp(ip);
        deviceInfoDto.setActive(active);
        return deviceInfoDto;
    }

    private boolean checkActive(Timestamp timeoutTimestamp) {
        if (!active) {
            return active;
        } else {
            return updateTime.after(timeoutTimestamp) ? true : false;
        }

    }
}
