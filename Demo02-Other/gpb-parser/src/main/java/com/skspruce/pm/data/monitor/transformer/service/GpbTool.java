package com.skspruce.pm.data.monitor.transformer.service;

import com.skspruce.common.protobuf.Sksinfo;

/**
 * Created by sk-qianxiao on 2018/6/13.
 */
public class GpbTool {


    public static int bytesToIntLittleEndian(byte[] bytes) {
        if(bytes != null && bytes.length <= 4) {
            int res = 0;

            for(int i = 0; i < bytes.length; ++i) {
                res <<= 8;
                int temp = bytes[bytes.length - 1 - i] & 255;
                res |= temp;
            }

            return res;
        } else {
            throw new IllegalArgumentException("bytes is empty, or the length is exceed four!");
        }
    }

    public static String intToIpv4Str(int s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; ++i) {
            int b = s >>> (3 - i) * 8 & 255;
            sb.append(b);
            if (i < 3) {
                sb.append(".".charAt(0));
            }
        }

        return sb.toString();
    }

    public static String bytesToMacStr(byte[] bytes, MacSeparator macSeparator) {
        if (bytes != null && bytes.length == 6) {
            char[] hexChars = new char[bytes.length * 3 - 1];
            int i = 0;

            for (int j = 0; i < bytes.length; ++i) {
                int v = bytes[i] & 255;
                hexChars[j++] = HEX_ARRAY[v >>> 4];
                hexChars[j++] = HEX_ARRAY[v & 15];
                if (j < hexChars.length) {
                    hexChars[j++] = macSeparator.getSeparator().charAt(0);
                }
            }

            return new String(hexChars);
        } else {
            throw new IllegalArgumentException("bytes is empty, or the length is not six!");
        }
    }












    public static String bytesToMacStr(byte[] bytes) throws IllegalArgumentException {
        return bytesToMacStr(bytes, MacSeparator.COLON);
    }

    public static enum MacSeparator {
        COLON(":"),
        STRIKE("-");

        private String separator;

        private MacSeparator(String separator) {
            this.separator = separator;
        }

        public String getSeparator() {
            return this.separator;
        }
    }


    private static final String HEX_STRING = "0123456789ABCDEF";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String MAC_REGEX = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
    private static final String IPV$_REGEX = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
    private static final String IPV4_SEPARATOR = ".";
    private static final int MAC_BYTES = 6;
}
