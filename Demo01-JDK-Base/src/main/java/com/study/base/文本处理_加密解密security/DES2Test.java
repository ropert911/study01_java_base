package com.study.base.文本处理_加密解密security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

/**
 * 3DES算法
 */
public class DES2Test {


    public static void main(String[] args) {
        jdk3DES();
    }

    public static void jdk3DES() {
        String content = "要加密的内容";
        //密码，长度要是8的倍数
        String password = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

        String encodeResult = encode(content, password);
        System.out.println("加密后的结果:" + encodeResult);
        String decodeResult = decode(encodeResult, password);
        System.out.println("解密后的:" + decodeResult);
    }

    public static String encode(String content, String password) {
        try {
            //生成KEY
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            //keyGenerator.init(168);
//            keyGenerator.init(new SecureRandom());//默认长度
//            SecretKey secretKey = keyGenerator.generateKey();
//            byte[] bytesKey = secretKey.getEncoded();
//            DESedeKeySpec desedeKeySpec = new DESedeKeySpec(bytesKey);

            //KEY转换
            DESedeKeySpec desedeKeySpec = new DESedeKeySpec(password.getBytes());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desedeKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(content.getBytes());

            //base64转换
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String decode(String inputBase64String, String password) {
        try {
            byte[] inputArray = Base64.decodeBase64(inputBase64String);

            //KEY转换
            DESedeKeySpec desedeKeySpec = new DESedeKeySpec(password.getBytes());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desedeKeySpec);

            //解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(inputArray);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
