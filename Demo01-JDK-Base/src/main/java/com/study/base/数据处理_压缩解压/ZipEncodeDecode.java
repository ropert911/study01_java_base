package com.study.base.数据处理_压缩解压;


import com.google.common.base.Strings;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;
import java.io.FileInputStream;

import static net.lingala.zip4j.model.enums.EncryptionMethod.AES;

/**
 * Zip加密、解压
 */
public class ZipEncodeDecode {
    public static void zip(File currentDir, String toFilePath, String password) throws Exception {
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(toFilePath);
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        // 是否设置加密文件
        parameters.setEncryptFiles(true);
        // 设置加密算法
        parameters.setEncryptionMethod(AES);
        // 设置AES加密密钥的密钥强度
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        // 设置密码
        if (!Strings.isNullOrEmpty(password)) {
            zipFile.setPassword(password.toCharArray());
        }

        // 要打包的文件夹
        File[] fList = currentDir.listFiles();

        // 遍历test文件夹下所有的文件、文件夹
        for (File f : fList) {
            if (f.isDirectory()) {
                zipFile.addFolder(f, parameters);
            } else {
                zipFile.addFile(f, parameters);
            }
        }

        FileInputStream inputStream = new FileInputStream("C:\\Users\\sk-qianxiao\\Desktop\\可视化运维接口文档.docx");
        ZipParameters parameters2 = new ZipParameters(parameters);
        parameters2.setFileNameInZip("可视化运维接口文档.docx");
        zipFile.addStream(inputStream, parameters2);
    }

    public static void unzip(String zipFilePath, String toPath, String password) throws Exception {
        // 生成的压缩文件
        ZipFile zipFile = new ZipFile(zipFilePath);
        // 设置密码
        if (!Strings.isNullOrEmpty(password)) {
            zipFile.setPassword(password.toCharArray());
        }
        // 解压缩所有文件以及文件夹
        zipFile.extractAll(toPath);
    }

    public static void main(String[] args) throws Exception {
        try {
            String passwd = "haha";
            zip(new File("C:\\Users\\sk-qianxiao\\Desktop\\out"), "C:\\Users\\sk-qianxiao\\Desktop\\a.zip", "haha");
            unzip("C:\\Users\\sk-qianxiao\\Desktop\\a.zip", "C:\\Users\\sk-qianxiao\\Desktop\\out1", passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
