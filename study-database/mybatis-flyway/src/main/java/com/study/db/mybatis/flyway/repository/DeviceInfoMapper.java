package com.study.db.mybatis.flyway.repository;

import com.study.db.mybatis.flyway.pojo.DeviceInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 设备信息持久化类
 *
 * @author sk-qianxiao
 * @date 2020/4/17
 */
public interface DeviceInfoMapper {
    /**
     * 插入或更新设备信息
     *
     * @param cinfo
     */
    @Insert("INSERT INTO device_info (mac,model,version,ip,connected,create_time,update_time)  VALUES " +
            "(#{mac},#{model},#{version},#{ip},#{active},#{createTime},#{updateTime}) " +
            "ON conflict(mac) " +
            "DO UPDATE SET model = #{model}, version =#{version}, ip=#{ip},connected=#{active},update_time=#{updateTime}")
    void insert(DeviceInfo cinfo);

    /**
     * 删除一个设备信息
     *
     * @param mac
     */
    @Delete("DELETE FROM device_info WHERE mac =#{mac}")
    void delete(@Param("mac") String mac);

    /**
     * 使用mac列表删除记录
     *
     * @param macs
     */
    @Delete("<script>DELETE FROM device_info WHERE mac in " +
            "<foreach collection='macs' item='mac' open='(' separator=',' close=')'>#{mac}</foreach>" +
            "</script>")
    void deleteByMacs(@Param("macs") Collection<String> macs);

    /**
     * 设备连接状态更新
     *
     * @param mac
     * @param connected
     * @param updateTime
     */
    @Update("UPDATE device_info SET connected=#{connected} ,update_time=#{updateTime} WHERE mac=#{mac}")
    void updateStatus(@Param("mac") String mac, @Param("connected") Boolean connected, @Param("updateTime") Timestamp updateTime);

    /**
     * 批量更新设备的状态和更新时间
     *
     * @param macs
     * @param connected
     * @param updateTime
     */
    @Update("<script>UPDATE device_info SET connected=#{connected} ,update_time=#{updateTime} WHERE mac in " +
            "<foreach collection='macs' item='mac' open='(' separator=',' close=')'>#{mac}</foreach>" +
            "</script>")
    void batchUpdateStatus(@Param("macs") Collection<String> macs, @Param("connected") Boolean connected, @Param("updateTime") Timestamp updateTime);


    /**
     * 获取单个配置信息
     *
     * @param mac
     * @return
     */
    @Select("select * from device_info where mac = #{mac}")
    @Results({
            @Result(property = "mac", column = "mac"),
            @Result(property = "model", column = "model"),
            @Result(property = "version", column = "version"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "active", column = "connected"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    DeviceInfo getOne(@Param("mac") String mac);


    /**
     * 根据设备型号和设备列表，查看上过线的设备的详细信息
     *
     * @param model
     * @param macs
     * @param pagesize
     * @param startindex
     * @return
     */
    @Select("<script>select COUNT(1) OVER() as count, * from device_info where mac in  " +
            "<foreach collection='macs' item='mac' open='(' separator=',' close=')'>#{mac}</foreach>" +
            " and model=#{model} order by mac ASC limit #{pagesize} offset #{startindex}" +
            "</script>")
    @Results({
            @Result(property = "count", column = "count"),
            @Result(property = "mac", column = "mac"),
            @Result(property = "model", column = "model"),
            @Result(property = "version", column = "version"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "active", column = "connected"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<DeviceInfo> getByModelAndMacs(@Param("model") String model, @Param("macs") Collection<String> macs, @Param("pagesize") int pagesize, @Param("startindex") int startindex);

    /**
     * 根据设备MAC获取设备信息
     *
     * @param macs
     * @param pagesize
     * @param startindex
     * @return
     */
    @Select("<script>select COUNT(1) OVER() as count, * from device_info where mac in  " +
            "<foreach collection='macs' item='mac' open='(' separator=',' close=')'>#{mac}</foreach>" +
            " order by mac ASC limit #{pagesize} offset #{startindex}" +
            "</script>")
    @Results({
            @Result(property = "count", column = "count"),
            @Result(property = "mac", column = "mac"),
            @Result(property = "model", column = "model"),
            @Result(property = "version", column = "version"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "active", column = "connected"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<DeviceInfo> getByMacs(@Param("macs") Collection<String> macs, @Param("pagesize") int pagesize, @Param("startindex") int startindex);
}
