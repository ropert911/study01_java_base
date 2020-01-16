package com.study.base.java_io;

import java.io.*;

/**
 * Created by xq on 2019/3/5.
 */
public class File_Wirte {
    public static void main(String[] args) {
        String fileName = "C:/temp/newTemp.txt";
        String content = "new append!";


        File_Wirte.appendMethodA(fileName, content);  //按方法A追加文件 使用 RandomAccessFile
        File_Wirte.appendMethodB(fileName, content);  //使用FileWriter 追加写文件
    }

    /**
     * A方法追加文件：使用RandomAccessFile
     * @param fileName xx
     * @param content xx
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     * @param fileName xx
     * @param content xx
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
