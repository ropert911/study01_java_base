package com.study.db.mybatis.repository;

import com.study.db.mybatis.flyway.pojo.DeviceInfo;
import com.study.db.mybatis.flyway.repository.DeviceInfoMapper;
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

/**
 * 设备信息持久化测试类
 *
 * @author sk-qianxiao
 * @date 2020/4/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DeviceInfoMapperTest {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Test
    public void insert() {
        String devMac = "11:22:33:44:55:66";
        deviceInfoMapper.delete(devMac);

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMac(devMac);
        deviceInfo.setModel("WAI3320");
        deviceInfo.setVersion("3320_1.2.3.4");
        deviceInfo.setIp("1.2.3.4");
        deviceInfo.setActive(true);
        deviceInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfo.setUpdateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfoMapper.insert(deviceInfo);

        //插入检查
        DeviceInfo deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertTrue(deviceInfo1.getMac().equals(deviceInfo.getMac()));
        Assert.assertTrue(deviceInfo1.getModel().equals(deviceInfo.getModel()));
        Assert.assertTrue(deviceInfo1.getVersion().equals(deviceInfo.getVersion()));
        Assert.assertTrue(deviceInfo1.getIp().equals(deviceInfo.getIp()));
        Assert.assertTrue(deviceInfo1.getActive() == deviceInfo.getActive());
        Assert.assertTrue(deviceInfo1.getCreateTime().equals(deviceInfo.getCreateTime()));
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(deviceInfo.getUpdateTime()));

        //更新信息后更新,模拟重新上线
        deviceInfo.setModel("WAI3330");
        deviceInfo.setVersion("3320_1.2.3.5");
        deviceInfo.setIp("1.2.3.5");
        deviceInfo.setActive(true);
        deviceInfo.setUpdateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfoMapper.insert(deviceInfo);

        //检查信息
        deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertTrue(deviceInfo1.getMac().equals(deviceInfo.getMac()));
        Assert.assertTrue(deviceInfo1.getModel().equals(deviceInfo.getModel()));
        Assert.assertTrue(deviceInfo1.getVersion().equals(deviceInfo.getVersion()));
        Assert.assertTrue(deviceInfo1.getIp().equals(deviceInfo.getIp()));
        Assert.assertTrue(deviceInfo1.getActive() == deviceInfo.getActive());
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(deviceInfo.getUpdateTime()));

        //删除验证
        deviceInfoMapper.delete(devMac);
        deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertNull(deviceInfo1);
    }

    @Test
    public void updateStatus() {
        String devMac = "11:22:33:44:55:66";
        deviceInfoMapper.delete(devMac);

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMac(devMac);
        deviceInfo.setModel("WAI3320");
        deviceInfo.setVersion("3320_1.2.3.4");
        deviceInfo.setIp("1.2.3.4");
        deviceInfo.setActive(true);
        deviceInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfo.setUpdateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfoMapper.insert(deviceInfo);

        //插入检查
        DeviceInfo deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertTrue(deviceInfo1.getMac().equals(deviceInfo.getMac()));
        Assert.assertTrue(deviceInfo1.getModel().equals(deviceInfo.getModel()));
        Assert.assertTrue(deviceInfo1.getVersion().equals(deviceInfo.getVersion()));
        Assert.assertTrue(deviceInfo1.getIp().equals(deviceInfo.getIp()));
        Assert.assertTrue(deviceInfo1.getActive() == deviceInfo.getActive());
        Assert.assertTrue(deviceInfo1.getCreateTime().equals(deviceInfo.getCreateTime()));
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(deviceInfo.getUpdateTime()));

        //掉线
        Timestamp uptime = com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp();
        deviceInfoMapper.updateStatus(devMac, false, uptime);
        deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertFalse(deviceInfo1.getActive());
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(uptime));

        //删除验证
        deviceInfoMapper.delete(devMac);
        deviceInfo1 = deviceInfoMapper.getOne(devMac);
        Assert.assertNull(deviceInfo1);
    }

    @Test
    public void batchUpdateStatus() {
        String devMac = "11:22:33:44:55:66";
        String devMac2 = "11:22:33:44:55:67";
        deviceInfoMapper.delete(devMac);
        deviceInfoMapper.delete(devMac2);

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMac(devMac);
        deviceInfo.setModel("WAI3320");
        deviceInfo.setVersion("3320_1.2.3.4");
        deviceInfo.setIp("1.2.3.4");
        deviceInfo.setActive(false);
        deviceInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfo.setUpdateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfoMapper.insert(deviceInfo);
        deviceInfo.setMac(devMac2);
        deviceInfoMapper.insert(deviceInfo);
        DeviceInfo deviceInfo1 = deviceInfoMapper.getOne(devMac2);
        Assert.assertTrue(deviceInfo1.getActive() == deviceInfo.getActive());
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(deviceInfo.getUpdateTime()));


        List<String> macs = new ArrayList<>();
        macs.add(devMac);
        macs.add(devMac2);
        Timestamp uptime = com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp();
        deviceInfoMapper.batchUpdateStatus(macs, true, uptime);

        //判断更新成功
        deviceInfo1 = deviceInfoMapper.getOne(devMac2);
        Assert.assertTrue(deviceInfo1.getActive());
        Assert.assertTrue(deviceInfo1.getUpdateTime().equals(uptime));

        //删除
        deviceInfoMapper.delete(devMac);
        deviceInfoMapper.delete(devMac2);
    }

    @Test
    public void getByModelAdnMacs() {
        List<String> macs = new ArrayList<>();
        macs.add("11:22:33:44:55:60");
        macs.add("11:22:33:44:55:61");
        macs.add("11:22:33:44:55:62");
        macs.add("11:22:33:44:55:63");
        macs.add("11:22:33:44:55:64");
        macs.add("11:22:33:44:55:65");
        macs.add("11:22:33:44:55:66");
        macs.add("11:22:33:44:55:67");
        macs.add("11:22:33:44:55:68");
        macs.add("11:22:33:44:55:69");
        macs.add("11:22:33:44:55:6A");
        macs.add("11:22:33:44:55:6B");
        macs.add("11:22:33:44:55:6C");
        macs.add("11:22:33:44:55:6D");
        macs.add("11:22:33:44:55:6E");
        macs.add("11:22:33:44:55:6F");
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setModel("WAI3320");
        deviceInfo.setVersion("3320_1.2.3.4");
        deviceInfo.setIp("1.2.3.4");
        deviceInfo.setActive(true);
        deviceInfo.setCreateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        deviceInfo.setUpdateTime(com.skspruce.homeap.dcm.server.util.TimeUtil.getCurrentTimestamp());
        for (String mac : macs) {
            deviceInfo.setMac(mac);
            deviceInfoMapper.insert(deviceInfo);
        }

        List<DeviceInfo> deviceInfos = deviceInfoMapper.getByModelAndMacs("WAI3320", macs, 5, 0);
        Assert.assertTrue(deviceInfos.size() == 5);
        Assert.assertTrue(deviceInfos.get(0).getCount() == 16);
        deviceInfos = deviceInfoMapper.getByModelAndMacs("WAI3320", macs, 5, 5);
        Assert.assertTrue(deviceInfos.size() == 5);
        Assert.assertTrue(deviceInfos.get(0).getCount() == 16);
        deviceInfos = deviceInfoMapper.getByModelAndMacs("WAI3320", macs, 5, 10);
        Assert.assertTrue(deviceInfos.size() == 5);
        Assert.assertTrue(deviceInfos.get(0).getCount() == 16);
        deviceInfos = deviceInfoMapper.getByModelAndMacs("WAI3320", macs, 5, 15);
        Assert.assertTrue(deviceInfos.size() == 1);
        Assert.assertTrue(deviceInfos.get(0).getCount() == 16);

        deviceInfoMapper.deleteByMacs(macs);
        DeviceInfo deviceInfo1 = deviceInfoMapper.getOne("11:22:33:44:55:64");
        Assert.assertNull(deviceInfo1);
    }
}