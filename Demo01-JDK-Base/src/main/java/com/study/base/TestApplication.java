package com.study.base;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.List;

public class TestApplication {
    private static Logger logger = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) throws Exception {
        List<String> abc = new ArrayList<>();
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:10:DC:00\",\"04:8B:42:10:9E:20\",\"04:8B:42:28:C0:B3\",\"22:22:44:44:44:44\",\"22:22:44:44:44:66\",\"22:22:44:44:44:77\",\"44:22:44:44:66:68\",\"04:8B:42:28:98:5E\",\"22:22:44:44:44:78\",\"32:23:33:11:FF:DD\",\"32:23:33:11:FF:EE\",\"04:8B:42:35:93:55\",\"11:11:11:11:11:11\",\"12:11:22:12:22:22\",\"55:34:22:11:23:44\",\"88:12:22:44:55:12\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":7,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:28:C0:B3\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:10:DC:00\",\"04:8B:42:10:9E:20\",\"04:8B:42:28:C0:B3\",\"22:22:44:44:44:44\",\"22:22:44:44:44:66\",\"22:22:44:44:44:77\",\"44:22:44:44:66:68\",\"04:8B:42:28:98:5E\",\"22:22:44:44:44:78\",\"32:23:33:11:FF:DD\",\"32:23:33:11:FF:EE\",\"04:8B:42:35:93:55\",\"11:11:11:11:11:11\",\"12:11:22:12:22:22\",\"55:34:22:11:23:44\",\"88:12:22:44:55:12\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"11:77:44:77:77:77\"],\"deviceType\":1,\"treeAreaList\":null}");
        abc.add("{\"changeType\":1,\"deviceIdList\":null,\"deviceMacList\":[\"88:74:77:77:77:77\"],\"deviceType\":1,\"treeAreaList\":[{\"id\":58,\"parentId\":0,\"children\":[{\"id\":68,\"parentId\":0,\"children\":[{\"id\":69,\"parentId\":0,\"children\":null}]}]}]}");
        abc.add("{\"changeType\":6,\"deviceIdList\":null,\"deviceMacList\":[\"11:77:44:77:77:77\"],\"deviceType\":1,\"treeAreaList\":null}");
        abc.add("{\"changeType\":2,\"deviceIdList\":null,\"deviceMacList\":[\"88:74:77:77:77:77\"],\"deviceType\":1,\"treeAreaList\":[{\"id\":58,\"parentId\":0,\"children\":[{\"id\":68,\"parentId\":0,\"children\":[{\"id\":69,\"parentId\":0,\"children\":null}]}]}]}");
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:10:DC:00\",\"04:8B:42:10:9E:20\",\"04:8B:42:28:C0:B3\",\"22:22:44:44:44:44\",\"22:22:44:44:44:66\",\"22:22:44:44:44:77\",\"44:22:44:44:66:68\",\"04:8B:42:28:98:5E\",\"22:22:44:44:44:78\",\"32:23:33:11:FF:DD\",\"32:23:33:11:FF:EE\",\"04:8B:42:35:93:55\",\"11:11:11:11:11:11\",\"12:11:22:12:22:22\",\"55:34:22:11:23:44\",\"88:12:22:44:55:12\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"99:11:23:23:23:23\"],\"deviceType\":1,\"treeAreaList\":null}");
        abc.add("{\"changeType\":10,\"deviceIdList\":null,\"deviceMacList\":[\"BB:BB:CC:CC:00:09\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":1,\"deviceIdList\":null,\"deviceMacList\":[\"BB:BB:CC:CC:00:06\"],\"deviceType\":2,\"treeAreaList\":[{\"id\":64,\"parentId\":0,\"children\":[{\"id\":65,\"parentId\":0,\"children\":[{\"id\":67,\"parentId\":0,\"children\":null}]}]}]}");
        abc.add("{\"changeType\":7,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:35:93:55\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":4,\"deviceIdList\":null,\"deviceMacList\":[\"04:8B:42:10:DC:00\",\"04:8B:42:10:9E:20\",\"04:8B:42:28:C0:B3\",\"22:22:44:44:44:44\",\"22:22:44:44:44:66\",\"22:22:44:44:44:77\",\"44:22:44:44:66:68\",\"04:8B:42:28:98:5E\",\"22:22:44:44:44:78\",\"32:23:33:11:FF:DD\",\"32:23:33:11:FF:EE\",\"04:8B:42:35:93:55\",\"11:11:11:11:11:11\",\"12:11:22:12:22:22\",\"55:34:22:11:23:44\",\"88:12:22:44:55:12\",\"BB:BB:CC:CC:00:10\",\"BB:BB:CC:CC:00:09\",\"BB:BB:CC:CC:00:01\",\"BB:BB:CC:CC:00:02\",\"BB:BB:CC:CC:00:03\",\"BB:BB:CC:CC:00:04\",\"BB:BB:CC:CC:00:05\",\"BB:BB:CC:CC:00:06\",\"BB:BB:CC:CC:00:07\",\"BB:BB:CC:CC:00:08\"],\"deviceType\":4,\"treeAreaList\":null}");
        abc.add("{\"changeType\":6,\"deviceIdList\":null,\"deviceMacList\":[\"88:88:99:99:00:05\"],\"deviceType\":1,\"treeAreaList\":null}");
        abc.add("");
        abc.add("abc");







        for (String message : abc) {
            try {
                DeviceChangeModel deviceChangeModel = JSON.parseObject(message, DeviceChangeModel.class);
                deviceChangeModel.getChangeType();
            } catch (Exception e) {
                logger.warn("aaaaaaaaaaaaa[{}]", message, e);
            }
        }
    }
}

class AreaModel {
    private long iD;
    private String name;

    public AreaModel() {
    }

    public long getID() {
        return this.iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class TreeAreaModel extends AreaModel {
    private int level;
    private long parentId;
    private List<TreeAreaModel> children;

    public TreeAreaModel() {
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<TreeAreaModel> getChildren() {
        return this.children;
    }

    public void setChildren(List<TreeAreaModel> children) {
        this.children = children;
    }
}

class DeviceChangeModel {
    private int changeType;
    private List<String> deviceMacList;
    private int deviceType;
    private List<TreeAreaModel> treeAreaList;

    public DeviceChangeModel() {
    }

    public int getChangeType() {
        return this.changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public List<String> getDeviceMacList() {
        return this.deviceMacList;
    }

    public void setDeviceMacList(List<String> deviceMacList) {
        this.deviceMacList = deviceMacList;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public List<TreeAreaModel> getTreeAreaList() {
        return this.treeAreaList;
    }

    public void setTreeAreaList(List<TreeAreaModel> treeAreaList) {
        this.treeAreaList = treeAreaList;
    }
}