package com.study.db.mybatis.repository;


import com.study.db.mybatis.flyway.pojo.ConfigInfo;
import com.study.db.mybatis.flyway.repository.ActiveConfigInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 任务持久化
 *
 * @author sk-qianxiao
 * @date 2020/4/17
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ActiveConfigInfoMapperTest {
    @Autowired
    private ActiveConfigInfoMapper activeConfigInfoMapper;

    @Test
    public void insertGetGetAllDelete() {
        String devId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(devId);
        configInfo.setBusinessType("aaaa");
        configInfo.setConfigStatus(1);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);

        //查看
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(getInfo.getBusinessType().equals(configInfo.getBusinessType()));

        //批量查看
        List<ConfigInfo> getInfos = activeConfigInfoMapper.getAll();
        for (ConfigInfo cinfo : getInfos) {
            Assert.assertTrue(cinfo.getBusinessType().equals(configInfo.getBusinessType()));
        }

        //删除
        activeConfigInfoMapper.delete(devId);
        getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void deleteByIds() {
        List<ConfigInfo> configInfoList = new ArrayList<>();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(UUID.randomUUID().toString());
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA1");
        activeConfigInfoMapper.insert(configInfo);
        configInfoList.add(configInfo);

        String configId2 = UUID.randomUUID().toString();
        ConfigInfo configInfo2 = new ConfigInfo();
        configInfo2.setConfigId(configId2);
        configInfo2.setConfigInfo("config info need send");
        configInfo2.setConfigResult("result:");
        configInfo2.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo2.setDeviceId("deviceidA2");
        activeConfigInfoMapper.insert(configInfo2);
        configInfoList.add(configInfo2);

        activeConfigInfoMapper.deleteByIds(configInfoList);

        //删除成功
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(configId2);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void updateStatus() {
        String devId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(devId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        activeConfigInfoMapper.updateStatus(devId, 1);
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(getInfo.getConfigStatus() == 1);

        //删除
        activeConfigInfoMapper.delete(devId);
        getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void updateSendStatus() {
        String devId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(devId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        Timestamp configTime = com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp();
        activeConfigInfoMapper.updateSendStatus(devId, 2,
                configTime, "aa");
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(getInfo.getConfigStatus() == 2);
        Assert.assertTrue(configTime.equals(getInfo.getConfigTime()));

        //删除
        activeConfigInfoMapper.delete(devId);
        getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void updateConfigResult() {
        String devId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(devId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        String newConfigResult = "new rsult";
        activeConfigInfoMapper.updateConfigResult(devId, newConfigResult);
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(newConfigResult.equals(getInfo.getConfigResult()));

        //删除
        activeConfigInfoMapper.delete(devId);
        getInfo = activeConfigInfoMapper.getOne(devId);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void getAllNotSendConfig() {
        String configId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(configId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);

        List<ConfigInfo> configInfos = activeConfigInfoMapper.getAllNotSendConfig("deviceidA");
        Assert.assertNotNull(configInfos);

        //删除
        activeConfigInfoMapper.delete(configId);
        ConfigInfo getInfo = activeConfigInfoMapper.getOne(configId);
        Assert.assertTrue(null == getInfo);
    }

    @Test
    public void getByDeviceAndBusinessType() {
        String deviceId = "00:0C:29:2C:3E:1B";
        List<String> configIds = new ArrayList<>();
        configIds.add(UUID.randomUUID().toString());
        configIds.add(UUID.randomUUID().toString());
        configIds.add(UUID.randomUUID().toString());
        configIds.add(UUID.randomUUID().toString());
        configIds.add(UUID.randomUUID().toString());
        configIds.add(UUID.randomUUID().toString());

        ConfigInfo configInfo = new ConfigInfo();


        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId(deviceId);

        List<ConfigInfo> configInfoList = new ArrayList<>();
        for (String configId : configIds) {
            configInfo.setConfigId(configId);
            activeConfigInfoMapper.insert(configInfo);
            ConfigInfo configInfo1 = new ConfigInfo();
            configInfo1.setConfigId(configId);
            configInfoList.add(configInfo1);
        }

        List<ConfigInfo> configInfoList1 = activeConfigInfoMapper.getByDeviceAndBusinessType(deviceId, "");
        Assert.assertTrue(configInfoList1.size() > 0);


        activeConfigInfoMapper.deleteByIds(configInfoList);

        configInfoList1 = activeConfigInfoMapper.getByDeviceAndBusinessType(deviceId, "");
        Assert.assertTrue(configInfoList1.isEmpty());
    }

    @Test
    public void getByConfigIds() {
        String configId = UUID.randomUUID().toString();
        String configId2 = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(configId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        activeConfigInfoMapper.insert(configInfo);
        configInfo.setConfigId(configId2);
        activeConfigInfoMapper.insert(configInfo);

        List<String> configIds = new ArrayList<>();
        configIds.add(configId);
        configIds.add(configId2);
        List<ConfigInfo> configInfos = activeConfigInfoMapper.getByConfigIds(configIds);
        log.info("1111 {}", configInfos);
        Assert.assertTrue(configInfos.size() == 2);
        for (String id : configIds) {
            activeConfigInfoMapper.delete(id);
        }

        configInfos = activeConfigInfoMapper.getByConfigIds(configIds);
        Assert.assertTrue(configInfos.size() == 0);
    }
}
