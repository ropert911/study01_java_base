package com.study.base.文本处理_加密解密security;

import org.apache.commons.codec.binary.Base16;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全散列算法（英语：Secure Hash Algorithm，缩写为SHA）
 * SHA家族的五个算法，分别是SHA-1、SHA-224、SHA-256、SHA-384，和SHA-512，由美国国家安全局（NSA）所设计，并由美国国家标准与技术研究院（NIST）发布；是美国的政府标准。后四者有时并称为SHA-2。
 * SHA-1在许多安全协定中广为使用，包括TLS和SSL、PGP、SSH、S/MIME和IPsec，曾被视为是MD5（更早之前被广为使用的杂凑函数）的后继者。
 *
 * @author sk-qianxiao
 * @date 2020/1/15
 */
public class DigestTest {
    public static void main(String[] args) {
        String input = "123456";

        {   //md2-md4因为有缺陷不再使用
            byte[] byteArray = toDigestBytes(input, "MD2");
            String output = toHexString(byteArray);

            System.out.println(input + " 在MD2后转16进制==" + output);
            Base16 base16 = new Base16(false);
            System.out.println(input + " 在MD2后转16进制==" + base16.encodeAsString(byteArray));
        }
        {
            //MD5算法是不可逆的，所以被很多网站广泛使用保存密码
            /*
                MD5消息摘要算法（英语：MD5 Message-Digest Algorithm），一种被广泛使用的密码散列函数，可以产生出一个128位（16字节）的散列值（hash value），用于确保信息传输完整一致。
                MD5典型应用
                1）MD5的典型应用是对一段信息（Message）产生信息摘要（Message-Digest），以防止被篡改。
                2）MD5的典型应用是对一段Message(字节串)产生fingerprint(指纹），以防止被“篡改”。
                3）MD5还广泛用于操作系统的登陆认证上，如Unix、各类BSD系统登录密码、数字签名等诸多方面。
            */
            byte[] byteArray = toDigestBytes(input, "MD5");
            String output = toHexString(byteArray);
            System.out.println(input + " 在MD5后转16进制==" + output);
        }
        {
            //安全散列算法（英语：Secure Hash Algorithm，缩写为SHA） 由美国国家安全局（NSA）所设计
            //SHA用的就是SHA-1
            byte[] byteArray = toDigestBytes(input, "SHA");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA后转16进制==" + output);
        }
        {
            byte[] byteArray = toDigestBytes(input, "SHA-1");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA-1后转16进制==" + output);
        }
        {
            byte[] byteArray = toDigestBytes(input, "SHA-224");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA-224后转16进制==" + output);
        }
        {
            byte[] byteArray = toDigestBytes(input, "SHA-256");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA-256后转16进制==" + output);
        }
        {
            byte[] byteArray = toDigestBytes(input, "SHA-384");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA-384后转16进制==" + output);
        }
        {
            byte[] byteArray = toDigestBytes(input, "SHA-512");
            String output = toHexString(byteArray);
            System.out.println(input + " 在SHA-512后转16进制==" + output);
        }
//        {
//            //没有在SDK中
//            byte[] byteArray = toDigestBytes(input, "SHA3");
//            String output = toHexString(byteArray);
//            System.out.println(input + " 在SHA3后转16进制==" + output);
//        }

        {
            System.out.println(input + " HmacMD5转16进制==" + hmac(input, "password", "HmacMD5"));
            System.out.println(input + " HmacSHA1转16进制==" + hmac(input, "password", "HmacSHA1"));
            System.out.println(input + " HmacSHA224转16进制==" + hmac(input, "password", "HmacSHA224"));
            System.out.println(input + " HmacSHA256转16进制==" + hmac(input, "password", "HmacSHA256"));
            System.out.println(input + " HmacSHA384转16进制==" + hmac(input, "password", "HmacSHA384"));
            System.out.println(input + " HmacSHA512转16进制==" + hmac(input, "password", "HmacSHA512"));
        }
    }

    private static String hmac(String input, String key, String type) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("utf-8"), type);
            Mac mac = Mac.getInstance(type);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(input.getBytes("utf-8"));
            return Hex.encodeHexString(rawHmac);
        } catch (Exception e) {
        }

        return null;
    }

    private static byte[] toDigestBytes(String input, String type) {
        byte[] byteArray = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type);
            //重置摘要
            messageDigest.reset();
            messageDigest.update(input.getBytes("UTF-8"));

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值
            byteArray = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return byteArray;
    }


    /**
     * 转16进程
     *
     * @param byteArray
     * @return
     */
    private static String toHexString(byte[] byteArray) {
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }

        }
        return md5StrBuff.toString().toUpperCase();
    }
}
