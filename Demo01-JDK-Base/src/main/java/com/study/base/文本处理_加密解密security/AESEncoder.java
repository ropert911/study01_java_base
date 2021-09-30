package com.study.base.文本处理_加密解密security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 * AES对称加密和解密
 */
public class AESEncoder {
    /*
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String content, byte[] password) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
//            keygen.init(128, new SecureRandom("AES".getBytes()));
            //3.产生原始对称密钥
//            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
//            byte[] raw = original_key.getEncoded();

            //5.根据字节数组生成AES密钥
//            SecretKey key = new SecretKeySpec(raw, "AES");
            SecretKey key = new SecretKeySpec(password, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("UTF-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            return Base64.encodeBase64String(byte_AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    public static String AESEncode(String content, byte[] password, byte[] iv) {
        try {
            //5.根据字节数组生成AES密钥
            SecretKeySpec key = new SecretKeySpec(password, "AES");
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(iv);
            //6.根据指定算法AES自成密码器
            //算法/模式/补码方式(PKCS5Padding或NoPadding)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key, ips);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("UTF-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            return Base64.encodeBase64String(byte_AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    public static String AESDecode(String input, byte[] password) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
//            keygen.init(128, new SecureRandom("AES".getBytes()));
            //3.产生原始对称密钥
//            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
//            byte[] raw = original_key.getEncoded();

            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = Base64.decodeBase64(input);

            //5.根据字节数组生成AES密钥
//            SecretKey key = new SecretKeySpec(raw, "AES");
            SecretKey key = new SecretKeySpec(password, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);

            // 解密
            byte[] byte_decode = cipher.doFinal(byte_content);
            return new String(byte_decode, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    public static String AESDecode(String input, byte[] password, byte[] iv) {
        try {
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = Base64.decodeBase64(input);

            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(password, "AES");
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ips = new IvParameterSpec(iv);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key, ips);

            //解密
            byte[] byte_decode = cipher.doFinal(byte_content);
            return new String(byte_decode, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    public static void main(String[] args) throws Exception {
        String content = "要加密的内容";
        byte[] password = "9588028820109132".getBytes();
        //加密向量
        String iv = "sks0123456789012";


        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128/192/256位的随机源,根据传入的字节数组
        keygen.init(128, new SecureRandom("AES".getBytes()));
        //3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        password = original_key.getEncoded();
        System.out.println("生成的密钥,长度：" + password.length + " " + 8 * password.length + " 内容" + password);

        String encodeResult = AESEncoder.AESEncode(content, password);
        System.out.println("加密后的结果:" + encodeResult);
        String dicodeResult = AESEncoder.AESDecode(encodeResult, password);
        System.out.println("解密后的明文是：" + dicodeResult);

        String encodeResult2 = AESEncoder.AESEncode(content, password, iv.getBytes("UTF-8"));
        System.out.println("加密后的结果:" + encodeResult2);
        String dicodeResult2 = AESEncoder.AESDecode(encodeResult2, password, iv.getBytes("UTF-8"));
        System.out.println("解密后的明文是：" + dicodeResult2);
    }

}