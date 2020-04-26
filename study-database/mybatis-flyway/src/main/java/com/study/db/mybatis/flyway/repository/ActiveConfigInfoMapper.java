package com.study.db.mybatis.flyway.repository;

import com.study.db.mybatis.flyway.pojo.ConfigInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 活跃配置的持久化
 *
 * @author sk-qianxiao
 * @date 2020/4/17
 */
public interface ActiveConfigInfoMapper {
    /**
     * 插入配置
     *
     * @param cinfo
     */
    @Insert("INSERT INTO active_config(config_id,device_id,create_time,config_status,business_type,config_info,config_result) VALUES " +
            "(#{configId},#{deviceId},#{createTime},#{configStatus},#{businessType},#{configInfo},#{configResult})")
    void insert(ConfigInfo cinfo);

    /**
     * 删除单个配置
     *
     * @param configId
     */
    @Delete("DELETE FROM active_config WHERE config_id =#{configId}")
    void delete(@Param("configId") String configId);

    /**
     * 指定ID进行批量删除
     *
     * @param ids
     */
    @Delete("<script>delete  from active_config where config_id in " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id.configId}</foreach>" +
            "</script>")
    void deleteByIds(@Param("ids") List<ConfigInfo> ids);


    /**
     * 更新任务状态
     *
     * @param configId
     * @param configStatus
     */
    @Update("UPDATE active_config SET config_status=#{configStatus} WHERE config_id=#{configId}")
    void updateStatus(@Param("configId") String configId, @Param("configStatus") int configStatus);

    /**
     * 更新任务最后的结果
     *
     * @param configId
     * @param configResult
     */
    @Update("UPDATE active_config SET config_result=#{configResult} WHERE config_id =#{configId}")
    void updateConfigResult(@Param("configId") String configId, @Param("configResult") String configResult);

    /**
     * 更新状态和结果
     *
     * @param configId
     * @param configStatus
     * @param configResult
     */
    @Update("UPDATE active_config SET config_status=#{configStatus}, config_result=#{configResult} WHERE config_id =#{configId}")
    void updateConfigStatusAndResult(@Param("configId") String configId,
                                     @Param("configStatus") int configStatus,
                                     @Param("configResult") String configResult);


    /**
     * 更新任务下发状态
     *
     * @param configId     配置任务ID
     * @param configStatus 要更新的状态
     * @param configTime   配置下发时间
     * @param configResult 最近结果信息
     */
    @Update("UPDATE active_config SET config_status=#{configStatus},config_time=#{configTime},config_result=#{configResult} WHERE config_id =#{configId}")
    void updateSendStatus(@Param("configId") String configId, @Param("configStatus") int configStatus,
                          @Param("configTime") Timestamp configTime,
                          @Param("configResult") String configResult);

    /**
     * 获取单个配置信息
     *
     * @param configId
     * @return
     */
    @Select("SELECT * FROM active_config WHERE config_id = #{configId}")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    ConfigInfo getOne(@Param("configId") String configId);

    /**
     * 获取全部配置信息
     *
     * @return
     */
    @Select("SELECT * FROM active_config")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    List<ConfigInfo> getAll();


    /**
     * 获取单个设置全部没有下发的配置，并按创建时间升序
     *
     * @return
     */
    @Select("select * from active_config where device_id =#{deviceId} and config_status = 1 order by create_time ASC")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    List<ConfigInfo> getAllNotSendConfig(@Param("deviceId") String deviceId);

    /**
     * 根据设备ID和业务类型获取全部配置
     *
     * @param deviceId
     * @param businessType
     * @return
     */
    @Select("select * from active_config where device_id =#{deviceId} and business_type=#{businessType}")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    List<ConfigInfo> getByDeviceAndBusinessType(@Param("deviceId") String deviceId, @Param("businessType") String businessType);


    /**
     * 根据配置列表获取配置
     *
     * @param configIds
     * @return
     */
    @Select("<script>select * from active_config where config_id in " +
            "<foreach collection='configIds' item='configId' open='(' separator=',' close=')'>#{configId}</foreach>" +
            "</script>")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    List<ConfigInfo> getByConfigIds(@Param("configIds") Collection<String> configIds);

    /**
     * 获取所有的超时任务
     *
     * @param timeoutTime
     * @return
     */
    @Select("select * from active_config where create_time<#{timeoutTime}")
    @Results({
            @Result(property = "configId", column = "config_id"),
            @Result(property = "deviceId", column = "device_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "configTime", column = "config_time"),
            @Result(property = "configStatus", column = "config_status"),
            @Result(property = "businessType", column = "business_type"),
            @Result(property = "configInfo", column = "config_info"),
            @Result(property = "configResult", column = "config_result")
    })
    List<ConfigInfo> getAllTimeoutTask(@Param("timeoutTime") Timestamp timeoutTime);
}
