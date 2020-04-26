package com.study.db.mybatis.repository;


import com.study.db.mybatis.flyway.pojo.ConfigInfo;
import com.study.db.mybatis.flyway.repository.HistoryConfigInfoMapper;
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
public class HistoryConfigInfoMapperTest {
    @Autowired
    private HistoryConfigInfoMapper historyConfigInfoMapper;

    @Test
    public void batchInsert() {
        List<ConfigInfo> configInfos = new ArrayList<>();
        String configId1 = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(configId1);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        configInfos.add(configInfo);

        String configId2 = UUID.randomUUID().toString();
        ConfigInfo configInfo2 = new ConfigInfo();
        configInfo2.setConfigId(configId2);
        configInfo2.setConfigInfo("config info need send");
        configInfo2.setConfigResult("result:");
        configInfo2.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo2.setDeviceId("deviceidA");
        configInfos.add(configInfo2);

        historyConfigInfoMapper.batchInsert(configInfos);
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(configId2);
        Assert.assertNotNull(getInfo);

        historyConfigInfoMapper.deleteByIds(configInfos);
        getInfo = historyConfigInfoMapper.getOne(configId2);
        Assert.assertNull(getInfo);
    }

    @Test
    public void insertGetGetAllDelete() {
        String configId = UUID.randomUUID().toString();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setConfigId(configId);
        configInfo.setConfigInfo("config info need send");
        configInfo.setConfigResult("result:");
        configInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo.setDeviceId("deviceidA");
        historyConfigInfoMapper.insert(configInfo);

        //查看
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(configId);
        Assert.assertTrue(getInfo.getBusinessType().equals(configInfo.getBusinessType()));

        //批量查看
        List<ConfigInfo> getInfos = historyConfigInfoMapper.getAll();
        for (ConfigInfo cinfo : getInfos) {
            Assert.assertTrue(cinfo.getBusinessType().equals(configInfo.getBusinessType()));
        }

        //删除
        historyConfigInfoMapper.delete(configId);
        getInfo = historyConfigInfoMapper.getOne(configId);
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
        historyConfigInfoMapper.insert(configInfo);
        configInfoList.add(configInfo);

        String configId2 = UUID.randomUUID().toString();
        ConfigInfo configInfo2 = new ConfigInfo();
        configInfo2.setConfigId(configId2);
        configInfo2.setConfigInfo("config info need send");
        configInfo2.setConfigResult("result:");
        configInfo2.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        configInfo2.setDeviceId("deviceidA2");
        historyConfigInfoMapper.insert(configInfo2);
        configInfoList.add(configInfo2);

        historyConfigInfoMapper.deleteByIds(configInfoList);

        //删除成功
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(configId2);
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
        historyConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        historyConfigInfoMapper.updateStatus(devId, 1);
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(devId);
        Assert.assertTrue(getInfo.getConfigStatus() == 1);

        //删除
        historyConfigInfoMapper.delete(devId);
        getInfo = historyConfigInfoMapper.getOne(devId);
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
        historyConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        Timestamp configTime = com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp();
        historyConfigInfoMapper.updateSendStatus(devId, 1,
                configTime, "");
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(devId);
        Assert.assertTrue(getInfo.getConfigStatus() == 1);
        Assert.assertTrue(configTime.equals(getInfo.getConfigTime()));

        //删除
        historyConfigInfoMapper.delete(devId);
        getInfo = historyConfigInfoMapper.getOne(devId);
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
        historyConfigInfoMapper.insert(configInfo);

        //更新状态并检查
        String newConfigResult = "new rsult";
        historyConfigInfoMapper.updateConfigResult(devId, newConfigResult);
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(devId);
        Assert.assertTrue(newConfigResult.equals(getInfo.getConfigResult()));

        //删除
        historyConfigInfoMapper.delete(devId);
        getInfo = historyConfigInfoMapper.getOne(devId);
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
        historyConfigInfoMapper.insert(configInfo);

        List<ConfigInfo> configInfos = historyConfigInfoMapper.getAllNotSendConfig("deviceidA");
        Assert.assertNotNull(configInfos);

        //删除
        historyConfigInfoMapper.delete(configId);
        ConfigInfo getInfo = historyConfigInfoMapper.getOne(configId);
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
            historyConfigInfoMapper.insert(configInfo);
            ConfigInfo configInfo1 = new ConfigInfo();
            configInfo1.setConfigId(configId);
            configInfoList.add(configInfo1);
        }

        List<ConfigInfo> configInfoList1 = historyConfigInfoMapper.getByDeviceAndBusinessType(deviceId, "");
        Assert.assertTrue(configInfoList1.size() > 0);


        historyConfigInfoMapper.deleteByIds(configInfoList);

        configInfoList1 = historyConfigInfoMapper.getByDeviceAndBusinessType(deviceId, "");
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
        historyConfigInfoMapper.insert(configInfo);
        configInfo.setConfigId(configId2);
        historyConfigInfoMapper.insert(configInfo);

        List<String> configIds = new ArrayList<>();
        configIds.add(configId);
        configIds.add(configId2);
        List<ConfigInfo> configInfos = historyConfigInfoMapper.getByConfigIds(configIds);
        log.info("1111 {}", configInfos);
        Assert.assertTrue(configInfos.size() == 2);
        for (String id : configIds) {
            historyConfigInfoMapper.delete(id);
        }

        configInfos = historyConfigInfoMapper.getByConfigIds(configIds);
        Assert.assertTrue(configInfos.size() == 0);
    }
}
