package com.skspruce.pm.data.monitor.transformer.service;

import com.skspruce.common.protobuf.Sksinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApGpbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApGpbService.class);


    public void makeApInfoForGpb(Sksinfo.AP ap, long firstDomainId, long thirdDomainIdDomainId) {

        //logging  AP.APBase
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("===========================AP(AcBaseMac:{}," +
                            "ApBaseMac:{}," +
                            "BinStartTime:{}, " +
                            "bin_vaildtime:{} " +
                            "FirstDomainId:{}," +
                            "ThirdDomainIdDomainId)======================",
                    ap.getApBase().getAcBaseMac(),
                    ap.getApBase().getApBaseMac(),
                    ap.getApBase().getBinStartTime(),
                    ap.getBinVaildtime(),
                    firstDomainId,
                    thirdDomainIdDomainId);
            LOGGER.debug("================================Ap.ApBase===========================");
            LOGGER.debug("ApBase.binStartTime:{}", ap.getApBase().getBinStartTime());
            LOGGER.debug("ApBase.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getApBase().getApBaseMac().toByteArray()));
            LOGGER.debug("ApBase.apBaseSn:{}", ap.getApBase().getApBaseSn());
            LOGGER.debug("ApBase.apBaseDeviceType:{}", ap.getApBase().getApBaseDeviceType());
            LOGGER.debug("ApBase.apBaseVendorId:{}", ap.getApBase().getApBaseVendorId());
            LOGGER.debug("ApBase.apBaseHwVersion:{}", ap.getApBase().getApBaseHwVersion());
            LOGGER.debug("ApBase.apBaseBkSoftVersion:{}", ap.getApBase().getApBaseBkSoftVersion());
            LOGGER.debug("ApBase.apBaseBootVersion:{}", ap.getApBase().getApBaseBootVersion());
            LOGGER.debug("ApBase.apBaseBkSoftVersion:{}", ap.getApBase().getApBaseBkSoftVersion());
            LOGGER.debug("ApBase.apBaseRadioNumber:{}",ap.getApBase().getApBaseRadioNumber().toByteArray());
            LOGGER.debug("ApBase.apBaseEthNumber:{}", ap.getApBase().getApBaseEthNumber().toByteArray());
            LOGGER.debug("ApBase.apBaseCpuName:{}", ap.getApBase().getApBaseCpuName());
            LOGGER.debug("ApBase.apBaseCpuFreq:{}", ap.getApBase().getApBaseCpuFreq());
            LOGGER.debug("ApBase.aApBaseMemory:{}", ap.getApBase().getApBaseMemory());
            LOGGER.debug("ApBase.apBaseHostname:{}", ap.getApBase().getApBaseHostname());
            LOGGER.debug("ApBase.apBaseLocation:{}", ap.getApBase().getApBaseLocation());
            LOGGER.debug("ApBase.apBaseGps:{}", ap.getApBase().getApBaseGps());
            LOGGER.debug("ApBase.acBaseMac:{}", GpbTool.bytesToMacStr(ap.getApBase().getAcBaseMac().toByteArray()));
            LOGGER.debug("ApBase.apBaseCpuNum:{}", ap.getApBase().getApBaseCpuNum());
        }



        //logging  AP.APStatus
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================Ap.APStatus===========================");
            LOGGER.debug("APStatus.binStartTime:{}", ap.getApStatus().getBinStartTime());
            LOGGER.debug("APStatus.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getApStatus().getApBaseMac().toByteArray()));
            LOGGER.debug("APStatus.apBaseIp:{}", GpbTool.intToIpv4Str(ap.getApStatus().getApBaseIp()));
            LOGGER.debug("APStatus.apStatusConnectAc:{}", GpbTool.bytesToIntLittleEndian(ap.getApStatus().getApStatusConnectAc().toByteArray()));
            LOGGER.debug("APStatus.apStatusSysuptime:{}", ap.getApStatus().getApStatusSysuptime());
            LOGGER.debug("APStatus.apStatusOnlineTime:{}", ap.getApStatus().getApStatusOnlineTime());
            LOGGER.debug("APStatus.apStatusMemRtUsage:{}", ap.getApStatus().getApStatusMemRtUsage().toByteArray());
            LOGGER.debug("APStatus.apStatusMemAvgUsage:{}", ap.getApStatus().getApStatusMemAvgUsage().toByteArray());
            LOGGER.debug("APStatus.apStatusRebootCause:{}", ap.getApStatus().getApStatusRebootCause());
            LOGGER.debug("APStatus.apStatusSysstartTime:{}", ap.getApStatus().getApStatusSysstartTime());
            LOGGER.debug("APStatus.acIp:{}", GpbTool.intToIpv4Str(ap.getApStatus().getAcIp()));
            LOGGER.debug("APStatus.acConnectTime:{}", ap.getApStatus().getAcConnectTime());
        }

        //logging  AP.APCPU
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.APCPU===========================");
            for (int i = 0; i < ap.getApCpuCount(); i++) {
                LOGGER.debug("=============APCPU({})==============", i);
                LOGGER.debug("APCPU.binStartTime:{}", ap.getApCpu(i).getBinStartTime());
                LOGGER.debug("APCPU.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getApCpu(i).getApBaseMac().toByteArray()));
                LOGGER.debug("APCPU.apCpuUser:{}", ap.getApCpu(i).getApCpuUser());
                LOGGER.debug("APCPU.apCpuNice:{}", ap.getApCpu(i).getApCpuNice());
                LOGGER.debug("APCPU.apCpuSystem:{}", ap.getApCpu(i).getApCpuSystem());
                LOGGER.debug("APCPU.apCpuIowait:{}", ap.getApCpu(i).getApCpuIowait());
                LOGGER.debug("APCPU.apCpuSoftirq:{}", ap.getApCpu(i).getApCpuSoftirq());
                LOGGER.debug("APCPU.apCpuUtil:{}", ap.getApCpu(i).getApCpuUtil());
                LOGGER.debug("APCPU.apCpuIdle:{}", ap.getApCpu(i).getApCpuIdle());
                LOGGER.debug("APCPU.apCpuIndex:{}", ap.getApCpu(i).getApCpuIndex());
                LOGGER.debug("APCPU.apCpuName:{}", ap.getApCpu(i).getApCpuName().toByteArray());
                LOGGER.debug("APCPU.apCpuFreq:{}", ap.getApCpu(i).getApCpuFreq().toByteArray());
            }
        }

        //logging  AP.Ether
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.Ether===========================");
            for (int i = 0; i < ap.getEthStatsCount(); i++) {
                LOGGER.debug("=============Ether({})==============", i);
                LOGGER.debug("Ether.firstSampleTime:{}", ap.getEthStats(i).getFirstSampleTime());
                LOGGER.debug("Ether.binStartTime:{}", ap.getEthStats(i).getBinStartTime());
                LOGGER.debug("Ether.elantoAPRxBytes:{}", ap.getEthStats(i).getElantoAPRxBytes());
                LOGGER.debug("Ether.elantoAPRxPkts:{}", ap.getEthStats(i).getElantoAPRxPkts());
                LOGGER.debug("Ether.aPtoElanTxBytes:{}", ap.getEthStats(i).getAPtoElanTxBytes());
                LOGGER.debug("Ether.aPtoElanTxPkts:{}", ap.getEthStats(i).getAPtoElanTxPkts());
                LOGGER.debug("Ether.elantoAPRxBcastBytes:{}", ap.getEthStats(i).getElantoAPRxBcastBytes());
                LOGGER.debug("Ether.elantoAPRxPkts:{}", ap.getEthStats(i).getElantoAPRxPkts());
                LOGGER.debug("Ether.elantoAPRxMcastBytes:{}", ap.getEthStats(i).getElantoAPRxMcastBytes());
                LOGGER.debug("Ether.elantoAPRxMcastPkts:{}", ap.getEthStats(i).getElantoAPRxMcastPkts());
                LOGGER.debug("Ether.aPtoElanTxBcastBytes:{}", ap.getEthStats(i).getAPtoElanTxBcastBytes());
                LOGGER.debug("Ether.aPtoElanTxBcastPkts:{}", ap.getEthStats(i).getAPtoElanTxBcastPkts());
                LOGGER.debug("Ether.aPtoElanTxMcastBytes:{}", ap.getEthStats(i).getAPtoElanTxMcastBytes());
                LOGGER.debug("Ether.aPtoElanTxMcastPkts:{}", ap.getEthStats(i).getAPtoElanTxMcastPkts());
                LOGGER.debug("Ether.wANtoAPRxErrPkts:{}", ap.getEthStats(i).getWANtoAPRxErrPkts());
                LOGGER.debug("Ether.aPtoWANTxErrPkts:{}", ap.getEthStats(i).getAPtoWANTxErrPkts());
                LOGGER.debug("Ether.LANIfRate:{}", ap.getEthStats(i).getLANIfRate());
                LOGGER.debug("Ether.LANIfSpeed:{}", ap.getEthStats(i).getLANIfSpeed());
                LOGGER.debug("Ether.LANUpDownTimes:{}", ap.getEthStats(i).getLANUpDownTimes());
                LOGGER.debug("Ether.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getEthStats(i).getApBaseMac().toByteArray()));
                LOGGER.debug("Ether.etherLanMac:{}", GpbTool.bytesToMacStr(ap.getEthStats(i).getEtherLanMac().toByteArray()));
                LOGGER.debug("Ether.etherLanInterfaceName:{}", ap.getEthStats(i).getEtherLanInterfaceName());
                LOGGER.debug("Ether.etherId:{}", ap.getEthStats(i).getEtherId().toByteArray());
                LOGGER.debug("Ether.vlanId:{}", ap.getEthStats(i).getVlanId());
            }
        }

        //logging  AP.WanPortStats
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.WanPortStats===========================");
            for (int i = 0; i < ap.getWanStatsCount(); i++) {
                LOGGER.debug("=============WanPortStats({})==============", i);
                LOGGER.debug("WanPortStats.firstSampleTime:{}", ap.getWanStats(i).getFirstSampleTime());
                LOGGER.debug("WanPortStats.binStartTime:{}", ap.getWanStats(i).getBinStartTime());
                LOGGER.debug("WanPortStats.WANtoAPRxBytes:{}", ap.getWanStats(i).getWANtoAPRxBytes());
                LOGGER.debug("WanPortStats.WANtoAPRxPkts:{}", ap.getWanStats(i).getWANtoAPRxPkts());
                LOGGER.debug("WanPortStats.aPtoWANTxBytes:{}", ap.getWanStats(i).getAPtoWANTxBytes());
                LOGGER.debug("WanPortStats.aPtoWANTxPkts:{}", ap.getWanStats(i).getAPtoWANTxPkts());
                LOGGER.debug("WanPortStats.WANtoAPRxBcastBytes:{}", ap.getWanStats(i).getWANtoAPRxBcastBytes());
                LOGGER.debug("WanPortStats.WANtoAPRxBcastPkts:{}", ap.getWanStats(i).getWANtoAPRxBcastPkts());
                LOGGER.debug("WanPortStats.WANtoAPRxMcastBytes:{}", ap.getWanStats(i).getWANtoAPRxMcastBytes());
                LOGGER.debug("WanPortStats.WANtoAPRxMcastPkts:{}", ap.getWanStats(i).getWANtoAPRxMcastPkts());
                LOGGER.debug("WanPortStats.WANtoAPRxErrPkts:{}", ap.getWanStats(i).getWANtoAPRxErrPkts());
                LOGGER.debug("WanPortStats.aPtoWANTxBcastBytes:{}", ap.getWanStats(i).getAPtoWANTxBcastBytes());
                LOGGER.debug("WanPortStats.aPtoWANTxBcastPkts:{}", ap.getWanStats(i).getAPtoWANTxBcastPkts());
                LOGGER.debug("WanPortStats.aPtoWANTxMcastBytes:{}", ap.getWanStats(i).getAPtoWANTxMcastBytes());
                LOGGER.debug("WanPortStats.aPtoWANTxMcastPkts:{}", ap.getWanStats(i).getAPtoWANTxMcastPkts());
                LOGGER.debug("WanPortStats.aPtoWANTxErrPkts:{}", ap.getWanStats(i).getAPtoWANTxErrPkts());
                LOGGER.debug("WanPortStats.WANIfRate:{}", ap.getWanStats(i).getWANIfRate());
                LOGGER.debug("WanPortStats.WANIfSpeed:{}", ap.getWanStats(i).getWANIfSpeed());
                LOGGER.debug("WanPortStats.upDownTimes:{}", ap.getWanStats(i).getUpDownTimes());
                LOGGER.debug("WanPortStats.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getWanStats(i).getApBaseMac().toByteArray()));
                LOGGER.debug("WanPortStats.wanportMac:{}", GpbTool.bytesToMacStr(ap.getWanStats(i).getWanportMac().toByteArray()));
                LOGGER.debug("WanPortStats.wanportType:{}", ap.getWanStats(i).getWanportType().toByteArray());
                LOGGER.debug("WanPortStats.wanportInterfaceName:{}", ap.getWanStats(i).getWanportInterfaceName());
                LOGGER.debug("WanPortStats.vlanId:{}", ap.getWanStats(i).getVlanId());
                LOGGER.debug("WanPortStats.wanportIp:{}", GpbTool.intToIpv4Str(ap.getWanStats(i).getWanportIp()));
            }
        }

        //logging  AP.ChannelBinStats
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.ChannelBinStats===========================");
            for (int i = 0; i < ap.getChannelStatsCount(); i++) {
                LOGGER.debug("=============ChannelBinStats({})==============", i);
                LOGGER.debug("ChannelBinStats.firstSampleTime:{}", ap.getChannelStats(i).getFirstSampleTime());
                LOGGER.debug("ChannelBinStats.binStartTime:{}", ap.getChannelStats(i).getBinStartTime());
                LOGGER.debug("ChannelBinStats.airTx:{}", ap.getChannelStats(i).getAirTx().toByteArray());
                LOGGER.debug("ChannelBinStats.airRx:{}", ap.getChannelStats(i).getAirRx().toByteArray());
                LOGGER.debug("ChannelBinStats.airBusy:{}", ap.getChannelStats(i).getAirBusy().toByteArray());
                LOGGER.debug("ChannelBinStats.noise:{}", ap.getChannelStats(i).getNoise().toByteArray());
                LOGGER.debug("ChannelBinStats.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getChannelStats(i).getApBaseMac().toByteArray()));
                LOGGER.debug("ChannelBinStats.radioId:{}", ap.getChannelStats(i).getRadioId().toByteArray());
                LOGGER.debug("ChannelBinStats.channel:{}", GpbTool.bytesToIntLittleEndian(ap.getChannelStats(i).getChannel().toByteArray()));
                LOGGER.debug("ChannelBinStats.txpower:{}", ap.getChannelStats(i).getTxpower().toByteArray());
                LOGGER.debug("ChannelBinStats.apRadioType:{}", ap.getChannelStats(i).getApRadioType());
            }
        }

        //logging  AP.VAPBinStats
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.VAPBinStats===========================");
            for (int i = 0; i < ap.getVapStatsCount(); i++) {
                LOGGER.debug("=============VAPBinStats({})==============", i);
                LOGGER.debug("VAPBinStats.vapSsidEncodeType:{}", ap.getVapStats(i).getVapSsidEncodeType().toByteArray());
                LOGGER.debug("VAPBinStats.radioId:{}", ap.getVapStats(i).getRadioId().toByteArray());
                LOGGER.debug("VAPBinStats.wlanKey(byte[int]):{}", ap.getVapStats(i).getWlanKey().toByteArray());
                LOGGER.debug("VAPBinStats.wlanKey:{}", new String(ap.getVapStats(i).getWlanKey().toByteArray()));
                LOGGER.debug("VAPBinStats.apRadioType:{}", ap.getVapStats(i).getApRadioType());
                LOGGER.debug("VAPBinStats.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getVapStats(i).getApBaseMac().toByteArray()));
                LOGGER.debug("VAPBinStats.securityType:{}", ap.getVapStats(i).getSecurityType().toByteArray());
                LOGGER.debug("VAPBinStats.encryptionType:{}", ap.getVapStats(i).getEncryptionType().toByteArray());
                LOGGER.debug("VAPBinStats.authMode:{}", ap.getVapStats(i).getAuthMode().toByteArray());
                LOGGER.debug("VAPBinStats.vlanId:{}", ap.getVapStats(i).getVlanId());

                //BinTrafficCounter信息
                LOGGER.debug("VAPBinStats.trafficCount.firstSampleTime:{}", ap.getVapStats(i).getTrafficCount().getFirstSampleTime());
                LOGGER.debug("VAPBinStats.trafficCount.binStartTime:{}", ap.getVapStats(i).getTrafficCount().getBinStartTime());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxDataBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxDataBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxDataPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxDataPkts());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataBadBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataBadBytes());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataPkts:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataPkts());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataGoodTime:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataGoodTime());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataGoodBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataGoodBytes());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataBadBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataBadBytes());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataBadTime:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataBadTime());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataUnauthedBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataUnauthedBytes());
                LOGGER.debug("VAPBinStats.trafficCount.aPtoSTATxDataUnauthedPkts:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxDataUnauthedPkts());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxDataUnauthedBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxDataUnauthedBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxBcastBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxBcastBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxBcastPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxBcastPkts());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxMcastBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxMcastBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxMcastPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxMcastPkts());
                LOGGER.debug("VAPBinStats.trafficCount.APtoSTATxBcastBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxBcastBytes());
                LOGGER.debug("VAPBinStats.trafficCount.APtoSTATxBcastPkts:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxBcastPkts());
                LOGGER.debug("VAPBinStats.trafficCount.APtoSTATxMcastBytes:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxMcastBytes());
                LOGGER.debug("VAPBinStats.trafficCount.APtoSTATxMcastPkts:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxMcastPkts());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxMgmtBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxMgmtBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxMgmtPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxMgmtPkts());
                LOGGER.debug("VAPBinStats.trafficCount.APtoSTATxMgmtPkts:{}", ap.getVapStats(i).getTrafficCount().getAPtoSTATxMgmtPkts());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPTxMgmtBytes:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPTxMgmtBytes());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxFcsErrPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxFcsErrPkts());
                LOGGER.debug("VAPBinStats.trafficCount.STAtoAPRxMicErrPkts:{}", ap.getVapStats(i).getTrafficCount().getSTAtoAPRxMicErrPkts());

                //AssocCounter信息
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxL2AuthPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxL2AuthPkts());
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxAssocPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxAssocPkts());
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxReassocPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxReassocPkts());
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxDeauthPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxDeauthPkts());
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxDisassocPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxDisassocPkts());
                LOGGER.debug("VAPBinStats.assocCount.STAtoAPRxAssocDropPkts:{}", ap.getVapStats(i).getAssocCount().getSTAtoAPRxAssocDropPkts());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxL2AuthReject:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxL2AuthReject());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxDeauthPkts:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxDeauthPkts());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxDisassocPkts:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxDisassocPkts());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxErrDisconnectPkts:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxErrDisconnectPkts());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxAssocNoResourceTimes:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxAssocNoResourceTimes());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxAssocNsRateTimes:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxAssocNsRateTimes());
                LOGGER.debug("VAPBinStats.assocCount.APtoSTATxAssocOtherRejectTimes:{}", ap.getVapStats(i).getAssocCount().getAPtoSTATxAssocOtherRejectTimes());

                //SnrStats信息
                LOGGER.debug("VAPBinStats.Snr09Db:{}", ap.getVapStats(i).getVapSnr().getSnr09Db());
                LOGGER.debug("VAPBinStats.Snr912Db:{}", ap.getVapStats(i).getVapSnr().getSnr912Db());
                LOGGER.debug("VAPBinStats.Snr1215Db:{}", ap.getVapStats(i).getVapSnr().getSnr1215Db());
                LOGGER.debug("VAPBinStats.Snr1518Db:{}", ap.getVapStats(i).getVapSnr().getSnr1518Db());
                LOGGER.debug("VAPBinStats.Snr1821Db:{}", ap.getVapStats(i).getVapSnr().getSnr1821Db());
                LOGGER.debug("VAPBinStats.Snr2124Db:{}", ap.getVapStats(i).getVapSnr().getSnr2124Db());
                LOGGER.debug("VAPBinStats.Snr2427Db:{}", ap.getVapStats(i).getVapSnr().getSnr2427Db());
                LOGGER.debug("VAPBinStats.Snr2730Db:{}", ap.getVapStats(i).getVapSnr().getSnr2730Db());
                LOGGER.debug("VAPBinStats.Snr3033Db:{}", ap.getVapStats(i).getVapSnr().getSnr3033Db());
                LOGGER.debug("VAPBinStats.Snr33100Db:{}", ap.getVapStats(i).getVapSnr().getSnr33100Db());
                LOGGER.debug("VAPBinStats.Rssi9990Db:{}", ap.getVapStats(i).getVapSnr().getRssi9990Db());
                LOGGER.debug("VAPBinStats.Rssi9087Db:{}", ap.getVapStats(i).getVapSnr().getRssi9087Db());
                LOGGER.debug("VAPBinStats.Rssi8784Db:{}", ap.getVapStats(i).getVapSnr().getRssi8784Db());
                LOGGER.debug("VAPBinStats.Rssi8481Db:{}", ap.getVapStats(i).getVapSnr().getRssi8481Db());
                LOGGER.debug("VAPBinStats.Rssi8178Db:{}", ap.getVapStats(i).getVapSnr().getRssi8178Db());
                LOGGER.debug("VAPBinStats.Rssi7875Db:{}", ap.getVapStats(i).getVapSnr().getRssi7875Db());
                LOGGER.debug("VAPBinStats.Rssi7572Db:{}", ap.getVapStats(i).getVapSnr().getRssi7572Db());
                LOGGER.debug("VAPBinStats.Rssi7269Db:{}", ap.getVapStats(i).getVapSnr().getRssi7269Db());
                LOGGER.debug("VAPBinStats.Rssi6966Db:{}", ap.getVapStats(i).getVapSnr().getRssi6966Db());
                LOGGER.debug("VAPBinStats.Rssi6663Db:{}", ap.getVapStats(i).getVapSnr().getRssi6663Db());
                LOGGER.debug("VAPBinStats.Rssi6360Db:{}", ap.getVapStats(i).getVapSnr().getRssi6360Db());
                LOGGER.debug("VAPBinStats.Rssi600Db:{}", ap.getVapStats(i).getVapSnr().getRssi600Db());

                LOGGER.debug("VAPBinStats.vapSsid:{}", ap.getVapStats(i).getVapSsid());
                LOGGER.debug("VAPBinStats.vapCreateTimestamp:{}", ap.getVapStats(i).getVapCreateTimestamp());
                LOGGER.debug("VAPBinStats.vapDelTimestamp:{}", ap.getVapStats(i).getVapDelTimestamp());
            }
        }

        //logging  AP.ClientSessionStats
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.ClientSessionStats===========================");
            for (int i = 0; i < ap.getClientSessionCount(); i++) {
                LOGGER.debug("=============ClientSessionStats({})==============", i);

                //ClientIndex信息
                LOGGER.debug("ClientSessionStats.ClientIndex.wlanKey(byte[int]):{}", ap.getClientSession(i).getClientId().getWlanKey().toByteArray());
                LOGGER.debug("ClientSessionStats.ClientIndex.wlanKey:{}", new String(ap.getClientSession(i).getClientId().getWlanKey().toByteArray()));
                LOGGER.debug("ClientSessionStats.ClientIndex.ApRadioType:{}", ap.getClientSession(i).getClientId().getApRadioType());
                LOGGER.debug("ClientSessionStats.ClientIndex.VapSsid:{}", ap.getClientSession(i).getClientId().getVapSsid());
                LOGGER.debug("ClientSessionStats.ClientIndex.ClientRadioType:{}", ap.getClientSession(i).getClientId().getClientRadioType());
                LOGGER.debug("ClientSessionStats.ClientIndex.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getClientSession(i).getClientId().getApBaseMac().toByteArray()));
                LOGGER.debug("ClientSessionStats.ClientIndex.clientMac:{}", GpbTool.bytesToMacStr(ap.getClientSession(i).getClientId().getClientMac().toByteArray()));
                LOGGER.debug("ClientSessionStats.ClientIndex.Channel:{}", GpbTool.bytesToIntLittleEndian(ap.getClientSession(i).getClientId().getChannel().toByteArray()));
                LOGGER.debug("ClientSessionStats.ClientIndex.SessionId:{}", ap.getClientSession(i).getClientId().getSessionId());
                LOGGER.debug("ClientSessionStats.ClientIndex.MultiSessionIdList:{}", ap.getClientSession(i).getClientId().getMultiSessionIdList());

                //SessionTrafficCounter信息
                LOGGER.debug("ClientSessionStats.sessionTraffic.STAtoAPSessionRxDataBytes:{}", ap.getClientSession(i).getSessionTraffic().getSTAtoAPSessionRxDataBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.STAtoAPSessionRxDataPkts:{}", ap.getClientSession(i).getSessionTraffic().getSTAtoAPSessionRxDataPkts());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataBytes:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataPkts:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataPkts());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataBadBytes:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataBadBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataBadTime:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataBadTime());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataGoodTime:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataGoodTime());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataGoodBytes:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataGoodBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.STAtoAPSessionRxDataUnauthedBytes:{}", ap.getClientSession(i).getSessionTraffic().getSTAtoAPSessionRxDataUnauthedBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.STAtoAPSessionRxDataUnauthedPkts:{}", ap.getClientSession(i).getSessionTraffic().getSTAtoAPSessionRxDataUnauthedPkts());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataUnauthedBytes:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataUnauthedBytes());
                LOGGER.debug("ClientSessionStats.sessionTraffic.APtoSTASessionTxDataUnauthedPkts:{}", ap.getClientSession(i).getSessionTraffic().getAPtoSTASessionTxDataUnauthedPkts());

                //ClientActivityInfo信息
                LOGGER.debug("ClientSessionStats.clientActivity.AssociatedRequestTimestamp:{}", ap.getClientSession(i).getClientActivity().getAssociatedRequestTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.AuthorizedTimestamp:{}", ap.getClientSession(i).getClientActivity().getAuthorizedTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DisconnectedTimestamp:{}", ap.getClientSession(i).getClientActivity().getDisconnectedTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.IpAssignedTimestamp:{}", ap.getClientSession(i).getClientActivity().getIpAssignedTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.ActivityBitmask:{}", ap.getClientSession(i).getClientActivity().getActivityBitmask());
                LOGGER.debug("ClientSessionStats.clientActivity.DisconnectedReason:{}", new String(ap.getClientSession(i).getClientActivity().getDisconnectedReason().toByteArray()));
                LOGGER.debug("ClientSessionStats.clientActivity.AssociatedResponseTimestamp:{}", ap.getClientSession(i).getClientActivity().getAssociatedResponseTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.MacAuthStartTimestamp:{}", ap.getClientSession(i).getClientActivity().getMacAuthStartTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.MacAuthOkTimestamp:{}", ap.getClientSession(i).getClientActivity().getMacAuthOkTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.Ieee8021XStartTimestamp:{}", ap.getClientSession(i).getClientActivity().getIeee8021XStartTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.Ieee8021XOkTimestamp:{}", ap.getClientSession(i).getClientActivity().getIeee8021XOkTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.WpaStartTimestamp:{}", ap.getClientSession(i).getClientActivity().getWpaStartTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.WpaOkTimestamp:{}", ap.getClientSession(i).getClientActivity().getWpaOkTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxdiscoverTimestamp:{}", ap.getClientSession(i).getClientActivity().getDhcpRxdiscoverTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpTxOffTimestamp:{}", ap.getClientSession(i).getClientActivity().getDhcpTxOffTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxRequestTimestamp:{}", ap.getClientSession(i).getClientActivity().getDhcpRxRequestTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpTxAckTimestamp:{}", ap.getClientSession(i).getClientActivity().getDhcpTxAckTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxRenewTimestamp:{}", ap.getClientSession(i).getClientActivity().getDhcpRxRenewTimestamp());
                LOGGER.debug("ClientSessionStats.clientActivity.AssociatedRequestPkts:{}", ap.getClientSession(i).getClientActivity().getAssociatedRequestPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.AssociatedResponsOkPkts:{}", ap.getClientSession(i).getClientActivity().getAssociatedResponsOkPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.MacAuthStartPkts:{}", ap.getClientSession(i).getClientActivity().getMacAuthStartPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.MacAuthOkPkts:{}", ap.getClientSession(i).getClientActivity().getMacAuthOkPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.Ieee8021XStartPkts:{}", ap.getClientSession(i).getClientActivity().getIeee8021XStartPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.Ieee8021XOkPkts:{}", ap.getClientSession(i).getClientActivity().getIeee8021XOkPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.WpaStartPkts:{}", ap.getClientSession(i).getClientActivity().getWpaStartPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.WpaOkPkts:{}", ap.getClientSession(i).getClientActivity().getWpaOkPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxdiscoverPkts:{}", ap.getClientSession(i).getClientActivity().getDhcpRxdiscoverPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpTxOffPkts:{}", ap.getClientSession(i).getClientActivity().getDhcpTxOffPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxRequestPkts:{}", ap.getClientSession(i).getClientActivity().getDhcpRxRequestPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpTxAckPkts:{}", ap.getClientSession(i).getClientActivity().getDhcpTxAckPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.DhcpRxRenewPkts:{}", ap.getClientSession(i).getClientActivity().getDhcpRxRenewPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.AssocResponseFailedPkts:{}", ap.getClientSession(i).getClientActivity().getAssocResponseFailedPkts());
                LOGGER.debug("ClientSessionStats.clientActivity.AssocNoResourceTimes:{}", ap.getClientSession(i).getClientActivity().getAssocNoResourceTimes());
                LOGGER.debug("ClientSessionStats.clientActivity.AssocNsRateTimes:{}", ap.getClientSession(i).getClientActivity().getAssocNsRateTimes());
                LOGGER.debug("ClientSessionStats.clientActivity.AssocOtherRejectTimes:{}", ap.getClientSession(i).getClientActivity().getAssocOtherRejectTimes());

                //ClientInfo信息
                LOGGER.debug("ClientSessionStats.clientSession.getUserName:{}", ap.getClientSession(i).getClientSession().getUserName());
                LOGGER.debug("ClientSessionStats.clientSession.getHostName:{}", ap.getClientSession(i).getClientSession().getHostName());
                LOGGER.debug("ClientSessionStats.clientSession.getOsType:{}", ap.getClientSession(i).getClientSession().getOsType());
                LOGGER.debug("ClientSessionStats.clientSession.getUserIp:{}", GpbTool.intToIpv4Str(ap.getClientBstats(i).getClientStats().getUserIp()));
            }
        }


        //logging  AP.ClientBinStats
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("================================AP.ClientBinStats===========================");
            for (int i = 0; i < ap.getClientBstatsCount(); i++) {
                LOGGER.debug("=============ClientBinStats({})==============", i);

                LOGGER.debug("ClientBinStats.wlanKey(byte[int]):{}", ap.getClientBstats(i).getWlanKey().toByteArray());
                LOGGER.debug("ClientBinStats.wlanKey:{}", new String(ap.getClientBstats(i).getWlanKey().toByteArray()));
                LOGGER.debug("ClientBinStats.apRadioType:{}", ap.getClientBstats(i).getApRadioType());
                LOGGER.debug("ClientBinStats.radioId:{}", ap.getClientBstats(i).getRadioId().toByteArray());
                LOGGER.debug("ClientBinStats.clientRadioType:{}", ap.getClientBstats(i).getClientRadioType());
                LOGGER.debug("ClientBinStats.apBaseMac:{}", GpbTool.bytesToMacStr(ap.getClientBstats(i).getApBaseMac().toByteArray()));
                LOGGER.debug("ClientBinStats.clientMac:{}", GpbTool.bytesToMacStr(ap.getClientBstats(i).getClientMac().toByteArray()));
                LOGGER.debug("ClientBinStats.channel:{}", GpbTool.bytesToIntLittleEndian(ap.getClientBstats(i).getChannel().toByteArray()));
                LOGGER.debug("ClientBinStats.sessionId:{}", ap.getClientBstats(i).getSessionId());
                LOGGER.debug("ClientBinStats.multiSessionId:{}", ap.getClientBstats(i).getMultiSessionIdList());

                //BinTrafficCounter信息
                LOGGER.debug("ClientBinStats.trafficCount.firstSampleTime:{}", ap.getClientBstats(i).getBinTraffic().getFirstSampleTime());
                LOGGER.debug("ClientBinStats.trafficCount.binStartTime:{}", ap.getClientBstats(i).getBinTraffic().getBinStartTime());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxDataBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxDataBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxDataPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxDataPkts());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataBadBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataBadBytes());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataPkts:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataPkts());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataGoodTime:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataGoodTime());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataGoodBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataGoodBytes());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataBadBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataBadBytes());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataBadTime:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataBadTime());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataUnauthedBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataUnauthedBytes());
                LOGGER.debug("ClientBinStats.trafficCount.aPtoSTATxDataUnauthedPkts:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxDataUnauthedPkts());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxDataUnauthedBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxDataUnauthedBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxBcastBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxBcastBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxBcastPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxBcastPkts());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxMcastBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxMcastBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxMcastPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxMcastPkts());
                LOGGER.debug("ClientBinStats.trafficCount.APtoSTATxBcastBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxBcastBytes());
                LOGGER.debug("ClientBinStats.trafficCount.APtoSTATxBcastPkts:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxBcastPkts());
                LOGGER.debug("ClientBinStats.trafficCount.APtoSTATxMcastBytes:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxMcastBytes());
                LOGGER.debug("ClientBinStats.trafficCount.APtoSTATxMcastPkts:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxMcastPkts());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxMgmtBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxMgmtBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxMgmtPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxMgmtPkts());
                LOGGER.debug("ClientBinStats.trafficCount.APtoSTATxMgmtPkts:{}", ap.getClientBstats(i).getBinTraffic().getAPtoSTATxMgmtPkts());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPTxMgmtBytes:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPTxMgmtBytes());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxFcsErrPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxFcsErrPkts());
                LOGGER.debug("ClientBinStats.trafficCount.STAtoAPRxMicErrPkts:{}", ap.getClientBstats(i).getBinTraffic().getSTAtoAPRxMicErrPkts());

                //ClientActivityInfo信息
                LOGGER.debug("ClientBinStats.clientActivity.AssociatedRequestTimestamp:{}", ap.getClientBstats(i).getClientActivity().getAssociatedRequestTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.AuthorizedTimestamp:{}", ap.getClientBstats(i).getClientActivity().getAuthorizedTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DisconnectedTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDisconnectedTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.IpAssignedTimestamp:{}", ap.getClientBstats(i).getClientActivity().getIpAssignedTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.ActivityBitmask:{}", ap.getClientBstats(i).getClientActivity().getActivityBitmask());
                LOGGER.debug("ClientBinStats.clientActivity.DisconnectedReason:{}", new String(ap.getClientSession(i).getClientActivity().getDisconnectedReason().toByteArray()));
                LOGGER.debug("ClientBinStats.clientActivity.AssociatedResponseTimestamp:{}", ap.getClientBstats(i).getClientActivity().getAssociatedResponseTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.MacAuthStartTimestamp:{}", ap.getClientBstats(i).getClientActivity().getMacAuthStartTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.MacAuthOkTimestamp:{}", ap.getClientBstats(i).getClientActivity().getMacAuthOkTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.Ieee8021XStartTimestamp:{}", ap.getClientBstats(i).getClientActivity().getIeee8021XStartTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.Ieee8021XOkTimestamp:{}", ap.getClientBstats(i).getClientActivity().getIeee8021XOkTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.WpaStartTimestamp:{}", ap.getClientBstats(i).getClientActivity().getWpaStartTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.WpaOkTimestamp:{}", ap.getClientBstats(i).getClientActivity().getWpaOkTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxdiscoverTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxdiscoverTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpTxOffTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDhcpTxOffTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxRequestTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxRequestTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpTxAckTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDhcpTxAckTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxRenewTimestamp:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxRenewTimestamp());
                LOGGER.debug("ClientBinStats.clientActivity.AssociatedRequestPkts:{}", ap.getClientBstats(i).getClientActivity().getAssociatedRequestPkts());
                LOGGER.debug("ClientBinStats.clientActivity.AssociatedResponsOkPkts:{}", ap.getClientBstats(i).getClientActivity().getAssociatedResponsOkPkts());
                LOGGER.debug("ClientBinStats.clientActivity.MacAuthStartPkts:{}", ap.getClientBstats(i).getClientActivity().getMacAuthStartPkts());
                LOGGER.debug("ClientBinStats.clientActivity.MacAuthOkPkts:{}", ap.getClientBstats(i).getClientActivity().getMacAuthOkPkts());
                LOGGER.debug("ClientBinStats.clientActivity.Ieee8021XStartPkts:{}", ap.getClientBstats(i).getClientActivity().getIeee8021XStartPkts());
                LOGGER.debug("ClientBinStats.clientActivity.Ieee8021XOkPkts:{}", ap.getClientBstats(i).getClientActivity().getIeee8021XOkPkts());
                LOGGER.debug("ClientBinStats.clientActivity.WpaStartPkts:{}", ap.getClientBstats(i).getClientActivity().getWpaStartPkts());
                LOGGER.debug("ClientBinStats.clientActivity.WpaOkPkts:{}", ap.getClientBstats(i).getClientActivity().getWpaOkPkts());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxdiscoverPkts:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxdiscoverPkts());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpTxOffPkts:{}", ap.getClientBstats(i).getClientActivity().getDhcpTxOffPkts());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxRequestPkts:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxRequestPkts());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpTxAckPkts:{}", ap.getClientBstats(i).getClientActivity().getDhcpTxAckPkts());
                LOGGER.debug("ClientBinStats.clientActivity.DhcpRxRenewPkts:{}", ap.getClientBstats(i).getClientActivity().getDhcpRxRenewPkts());
                LOGGER.debug("ClientBinStats.clientActivity.AssocResponseFailedPkts:{}", ap.getClientBstats(i).getClientActivity().getAssocResponseFailedPkts());
                LOGGER.debug("ClientBinStats.clientActivity.AssocNoResourceTimes:{}", ap.getClientBstats(i).getClientActivity().getAssocNoResourceTimes());
                LOGGER.debug("ClientBinStats.clientActivity.AssocNsRateTimes:{}", ap.getClientBstats(i).getClientActivity().getAssocNsRateTimes());
                LOGGER.debug("ClientBinStats.clientActivity.AssocOtherRejectTimes:{}", ap.getClientBstats(i).getClientActivity().getAssocOtherRejectTimes());

                //SnrStats信息
                LOGGER.debug("ClientBinStats.Snr09Db:{}", ap.getClientBstats(i).getClientSnr().getSnr09Db());
                LOGGER.debug("ClientBinStats.Snr912Db:{}", ap.getClientBstats(i).getClientSnr().getSnr912Db());
                LOGGER.debug("ClientBinStats.Snr1215Db:{}", ap.getClientBstats(i).getClientSnr().getSnr1215Db());
                LOGGER.debug("ClientBinStats.Snr1518Db:{}", ap.getClientBstats(i).getClientSnr().getSnr1518Db());
                LOGGER.debug("ClientBinStats.Snr1821Db:{}", ap.getClientBstats(i).getClientSnr().getSnr1821Db());
                LOGGER.debug("ClientBinStats.Snr2124Db:{}", ap.getClientBstats(i).getClientSnr().getSnr2124Db());
                LOGGER.debug("ClientBinStats.Snr2427Db:{}", ap.getClientBstats(i).getClientSnr().getSnr2427Db());
                LOGGER.debug("ClientBinStats.Snr2730Db:{}", ap.getClientBstats(i).getClientSnr().getSnr2730Db());
                LOGGER.debug("ClientBinStats.Snr3033Db:{}", ap.getClientBstats(i).getClientSnr().getSnr3033Db());
                LOGGER.debug("ClientBinStats.Snr33100Db:{}", ap.getClientBstats(i).getClientSnr().getSnr33100Db());
                LOGGER.debug("ClientBinStats.Rssi9990Db:{}", ap.getClientBstats(i).getClientSnr().getRssi9990Db());
                LOGGER.debug("ClientBinStats.Rssi9087Db:{}", ap.getClientBstats(i).getClientSnr().getRssi9087Db());
                LOGGER.debug("ClientBinStats.Rssi8784Db:{}", ap.getClientBstats(i).getClientSnr().getRssi8784Db());
                LOGGER.debug("ClientBinStats.Rssi8481Db:{}", ap.getClientBstats(i).getClientSnr().getRssi8481Db());
                LOGGER.debug("ClientBinStats.Rssi8178Db:{}", ap.getClientBstats(i).getClientSnr().getRssi8178Db());
                LOGGER.debug("ClientBinStats.Rssi7875Db:{}", ap.getClientBstats(i).getClientSnr().getRssi7875Db());
                LOGGER.debug("ClientBinStats.Rssi7572Db:{}", ap.getClientBstats(i).getClientSnr().getRssi7572Db());
                LOGGER.debug("ClientBinStats.Rssi7269Db:{}", ap.getClientBstats(i).getClientSnr().getRssi7269Db());
                LOGGER.debug("ClientBinStats.Rssi6966Db:{}", ap.getClientBstats(i).getClientSnr().getRssi6966Db());
                LOGGER.debug("ClientBinStats.Rssi6663Db:{}", ap.getClientBstats(i).getClientSnr().getRssi6663Db());
                LOGGER.debug("ClientBinStats.Rssi6360Db:{}", ap.getClientBstats(i).getClientSnr().getRssi6360Db());
                LOGGER.debug("ClientBinStats.Rssi600Db:{}", ap.getClientBstats(i).getClientSnr().getRssi600Db());

                //ClientInfo信息
                LOGGER.debug("ClientBinStats.clientSession.UserName:{}", ap.getClientBstats(i).getClientStats().getUserName());
                LOGGER.debug("ClientBinStats.clientSession.HostName:{}", ap.getClientBstats(i).getClientStats().getHostName());
                LOGGER.debug("ClientBinStats.clientSession.OsType:{}", ap.getClientBstats(i).getClientStats().getOsType());
                LOGGER.debug("ClientBinStats.clientSession.UserIp:{}", GpbTool.intToIpv4Str(ap.getClientBstats(i).getClientStats().getUserIp()));
            }
        }

    }
}
