package com.study.gpb.service;


import com.skspruce.common.protobuf.Sksinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author litao on 2017/6/20.
 */
@Service
public class ParserService {
    private static final Logger logger = LoggerFactory.getLogger(ParserService.class);

    public void printGpb(Sksinfo.AP ap) {
        printApBase(ap.getApBase());
        //session
        for (Sksinfo.ClientSessionStats clientSessionStats : ap.getClientSessionList()) {
            printSession(clientSessionStats);
        }
        //client
        for (Sksinfo.ClientBinStats clientBinStats : ap.getClientBstatsList()) {
            printClientInfo(clientBinStats);
        }
    }

    public void printApBase(Sksinfo.APBase apBase) {
        logger.info("APBase:getBinStartTime {}", apBase.getBinStartTime());
    }

    public void printClientInfo(Sksinfo.ClientBinStats clientBinStats) {

    }

    public void printSession(Sksinfo.ClientSessionStats clientSessionStats) {
        logger.info("=====================session");
        String clientMac = GpbTool.bytesToMacStr(clientSessionStats.getClientId().getClientMac().toByteArray());
        logger.info("ClientSessionStats:ClientIndex:client_mac {}", clientMac);

        int ip = clientSessionStats.getClientSession().getUserIp();
        if (0 == ip) {
            logger.info("ClientSessionStats:ClientInfo:user_ip {} 不在线：ap_assoc_all", ip);
            return;
        }
        else {
            String userIp = GpbTool.intToIpv4Str(ip);
            logger.info("ClientSessionStats:ClientInfo:user_ip {} 在线: ap_client_all, ap_assoc_all", userIp);
        }



//        ap_radio_frequency 为2 .4 G的
//                client_mac
//        STAtoAP_rx_data_bytes
//                APtoSTA_tx_data_bytes
//        associated_request_pkts
//                associated_respons_ok_pkts
//        associated_success_rate
//                associated_failed_pkts
//        auth_start_pkts
//                auth_ok_pkts
//        auth_success_rate
//                auth_failed_pkts
//        dhcp_rx_request_pkts
//                dhcp_rx_renew_pkts
//        dhcp_tx_ack_pkts
//                online_time

    }
}
