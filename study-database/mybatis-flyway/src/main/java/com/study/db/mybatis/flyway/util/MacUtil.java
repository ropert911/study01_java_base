package com.skspruce.homeap.dcm.server.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * MAC处理工具类
 *
 * @author sk-qianxiao
 * @date 2020/3/18
 */
public class MacUtil {
    static String localMac = "";

    /**
     * 获取本机mac地址
     *
     * @return
     */
    public static String getLocalMac() {
        if (localMac.length() > 0) {
            return localMac;
        }

        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
//                sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            localMac = sb.toString().toUpperCase();
            return localMac;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * MAC地址合法性检查
     *
     * @param macStr
     * @return
     */
    public static boolean isValidMac(String macStr) {

        if (macStr == null || macStr.equals("")) {
            return false;
        }
        String macAddressRule = "([A-Fa-f0-9]{2}[:]){5}[A-Fa-f0-9]{2}";
        // 这是真正的MAC地址；正则表达式；
        if (macStr.matches(macAddressRule)) {
            return true;
        } else {
            return false;
        }
    }
}
