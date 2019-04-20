package com.skspruce.pm.data.monitor.transformer.service;

import com.skspruce.common.protobuf.Sksinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AcGpbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcGpbService.class);

    public void makeAcInfoForGpb(Sksinfo.AC ac){

        //logging AC.AcBase
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC(AcBaseMac:{},BinStartTime:{})===========================",
                    ac.getAcBase().getAcBaseMac(),ac.getBinStartTime());
            LOGGER.debug("================================AC.AcBase===========================");
            LOGGER.debug("AcBase.acBaseMac:{}",ac.getAcBase().getAcBaseMac());
            LOGGER.debug("AcBase.acBaseCpuNum:{}",ac.getAcBase().getAcBaseCpuNum());
            LOGGER.debug("AcBase.acBaseSn:{}",ac.getAcBase().getAcBaseSn());
            LOGGER.debug("AcBase.acBaseDeviceType:{}",ac.getAcBase().getAcBaseDeviceType());
            LOGGER.debug("AcBase.acBaseHwVersion:{}",ac.getAcBase().getAcBaseHwVersion());
            LOGGER.debug("AcBase.acBaseBootVersion:{}",ac.getAcBase().getAcBaseBootVersion());
            LOGGER.debug("AcBase.acBaseSoftVersion:{}",ac.getAcBase().getAcBaseSoftVersion());
            LOGGER.debug("AcBase.acBaseHostname:{}",ac.getAcBase().getAcBaseHostname());
            LOGGER.debug("AcBase.acBaseVendorId:{}",ac.getAcBase().getAcBaseVendorId());
        }


        //logging AC.Status
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.Status===========================");
            LOGGER.debug("Status.acStatusMemAvgUsage:{}",ac.getAcStatus().getAcStatusMemAvgUsage());
            LOGGER.debug("Status.acIp:{}",ac.getAcStatus().getAcIp());
            LOGGER.debug("Status.acStatusSysuptime:{}",ac.getAcStatus().getAcStatusSysuptime());
            LOGGER.debug("Status.acStatusOnlineTime:{}",ac.getAcStatus().getAcStatusOnlineTime());
            LOGGER.debug("Status.acStatusVrrpEnable:{}",ac.getAcStatus().getAcStatusVrrpEnable());
            LOGGER.debug("Status.acStatusVrrpStatus:{}",ac.getAcStatus().getAcStatusVrrpStatus());
        }

        //logging  AC.ACCPCPU
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.ACCPCPU===========================");
            for(int i=0;i<ac.getAcCpCpuCount();i++){
                LOGGER.debug("=============ACCPCPUList({})==============",i);
                LOGGER.debug("ACCPCPU.acCpuUser:{}",ac.getAcCpCpu(i).getAcCpuUser());
                LOGGER.debug("ACCPCPU.acCpuNice:{}",ac.getAcCpCpu(i).getAcCpuNice());
                LOGGER.debug("ACCPCPU.acCpuSystem:{}",ac.getAcCpCpu(i).getAcCpuSystem());
                LOGGER.debug("ACCPCPU.acCpuIowait:{}",ac.getAcCpCpu(i).getAcCpuIowait());
                LOGGER.debug("ACCPCPU.acCpuSoftirq:{}",ac.getAcCpCpu(i).getAcCpuSoftirq());
                LOGGER.debug("ACCPCPU.acCpuUtil:{}",ac.getAcCpCpu(i).getAcCpuUtil());
                LOGGER.debug("ACCPCPU.acCpuIdle:{}",ac.getAcCpCpu(i).getAcCpuIdle());
                LOGGER.debug("ACCPCPU.acCpuName:{}",ac.getAcCpCpu(i).getAcCpuName());
                LOGGER.debug("ACCPCPU.acCpuFreq:{}",ac.getAcCpCpu(i).getAcCpuFreq());
            }
        }

        //logging  AC.ACDPCPU
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.ACDPCPU===========================");
            for(int i=0;i<ac.getAcDpCpuCount();i++){
                LOGGER.debug("=============ACDPCPUList({})==============",i);
                LOGGER.debug("ACDPCPU.acCpuUsage:{}",ac.getAcDpCpu(i).getAcCpuUsage());
                LOGGER.debug("ACDPCPU.acCpuIndex:{}",ac.getAcDpCpu(i).getAcCpuIndex());
                LOGGER.debug("ACDPCPU.acCpuName:{}",ac.getAcDpCpu(i).getAcCpuName());
                LOGGER.debug("ACDPCPU.acCpuFreq:{}",ac.getAcDpCpu(i).getAcCpuFreq());
            }
        }

        //logging AC.IfStatus
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AC.IfStatus===========================");
            for(int i=0;i<ac.getIfStatsCount();i++){
                LOGGER.debug("=============IfStatus({})==============",i);
                LOGGER.debug("IfStatus.acIfIndex:{}",ac.getIfStats(i).getAcIfIndex());
                LOGGER.debug("IfStatus.acIfName:{}",ac.getIfStats(i).getAcIfName());
                LOGGER.debug("IfStatus.acIfType:{}",ac.getIfStats(i).getAcIfType());
                LOGGER.debug("IfStatus.acIfStatus:{}",ac.getIfStats(i).getAcIfStatus());
                LOGGER.debug("IfStatus.acIfMac:{}",ac.getIfStats(i).getAcIfMac());
                LOGGER.debug("IfStatus.acIfIpv4:{}",ac.getIfStats(i).getAcIfIpv4());
                LOGGER.debug("IfStatus.acIfRate:{}",ac.getIfStats(i).getAcIfRate());
                LOGGER.debug("IfStatus.acIfInBytes:{}",ac.getIfStats(i).getAcIfInBytes());
                LOGGER.debug("IfStatus.acIfOutBytes:{}",ac.getIfStats(i).getAcIfOutBytes());
            }
        }

        //logging  AC.DhcpPoolStatus
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.DhcpPoolStatus===========================");
            for(int i=0;i<ac.getDhcppoolStatsCount();i++){
                LOGGER.debug("=============DhcpPoolStatus({})==============",i);
                LOGGER.debug("DhcpPoolStatus.dhcppoolName:{}",ac.getDhcppoolStats(i).getDhcppoolName());
                LOGGER.debug("DhcpPoolStatus.subNetIp:{}",ac.getDhcppoolStats(i).getSubNetIp());
                LOGGER.debug("DhcpPoolStatus.subNetMask:{}",ac.getDhcppoolStats(i).getSubNetMask());
                LOGGER.debug("DhcpPoolStatus.dhcppoolActiveLeases:{}",ac.getDhcppoolStats(i).getDhcppoolActiveLeases());
                LOGGER.debug("DhcpPoolStatus.dhcppoolFreeLeases:{}",ac.getDhcppoolStats(i).getDhcppoolFreeLeases());
                LOGGER.debug("DhcpPoolStatus.dhcppoolTotalLeases:{}",ac.getDhcppoolStats(i).getDhcppoolTotalLeases());
            }
        }

        //logging  AC.LicStatus
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.LicStatus===========================");
            for(int i=0;i<ac.getLicenseStatsCount();i++){
                LOGGER.debug("=============LicStatus({})==============",i);
                LOGGER.debug("LicStatus.licId:{}",ac.getLicenseStats(i).getLicId());
                LOGGER.debug("LicStatus.licInstallTime:{}",ac.getLicenseStats(i).getLicInstallTime());
                LOGGER.debug("LicStatus.licType:{}",ac.getLicenseStats(i).getLicType());
                LOGGER.debug("LicStatus.licControlType:{}",ac.getLicenseStats(i).getLicControlType());
                LOGGER.debug("LicStatus.licValue:{}",ac.getLicenseStats(i).getLicValue());
            }
        }

        //logging  AC.APinfo
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("================================AC.APinfo===========================");
            for(int i=0;i<ac.getApListCount();i++){
                LOGGER.debug("=============ApList({})==============",i);
                LOGGER.debug("APinfo.apBaseMac:{}",ac.getApList(i).getApBaseMac());
                LOGGER.debug("APinfo.apConnectStatus:{}",ac.getApList(i).getApConnectStatus());
                LOGGER.debug("APinfo.onlineTime:{}",ac.getApList(i).getOnlineTime());
                LOGGER.debug("APinfo.offlineTime:{}",ac.getApList(i).getOfflineTime());
            }
        }

    }
}
