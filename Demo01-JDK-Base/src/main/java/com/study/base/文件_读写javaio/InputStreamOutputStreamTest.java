package com.study.base.文件_读写javaio;


import java.io.*;

public class InputStreamOutputStreamTest {
    public static void main(String[] args) throws Exception {
        /**
         * 1.通过流复制一个图片的例子
         */
        File file = new File("c:/images/1.png");
        File outfile = new File("C:/temp.png");
        try (
                FileInputStream inputStream = new FileInputStream(file);
                FileOutputStream outputStream = new FileOutputStream(outfile);
        ) {
            int i = 0;
            while (i != -1) {
                i = inputStream.read();     //4字节，4字节读
                //还有两个方法
//                inputStream.read(buf);
//                inputStream.read(buf, off, len)
                outputStream.write(i);
            }
        }

        /**
         * 2.如果我们想提高要提高复制的速度，可以采用缓冲文件输入\输出流，如下：
         */
        try (
                FileInputStream inputStream = new FileInputStream("C:/images/1.png");
                FileOutputStream outputStream = new FileOutputStream("C:/temp1.jpg");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        ) {
            int i = 0;
            while (i != -1) {
                i = bufferedInputStream.read();
                bufferedOutputStream.write(i);
            }
            bufferedOutputStream.flush();//强制清除缓冲区的内容
        }

        /**
         * 3.当文件很大,我们要做一个缓冲处理来提高速度。如下：当文件的大小大于512个字节时，每次读入512个字节后再做处理
         */
        try (FileInputStream inputStream = new FileInputStream("C:/images/1.png");
             FileOutputStream outputStream = new FileOutputStream("C:/temp2.png")
        ) {
            int i = 0;
            byte[] buffer = new byte[512];
            while (true) {
                if (inputStream.available() < 512) {    //available可读取的字节数
                    while (i != -1) {
                        i = inputStream.read();
                        outputStream.write(i);
                    }
                    break;
                } else {
                    inputStream.read(buffer);       //按buffer大小读
                    outputStream.write(buffer);
                }
            }
        }

        /**
         * 4.根据上面的例子，我们可以知道：我们可以做一个双缓冲的文件复制
         */
        try (
                //文件输入流
                FileInputStream inputStream = new FileInputStream("C:/images/1.png");
                //文件输出流
                FileOutputStream outputStream = new FileOutputStream("C:/temp3.png");
                //缓冲文件输入流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                //缓冲文件输出流
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        ) {
            int i = 0;
            byte[] buffer = new byte[512];
            while (true) {
                if (bufferedInputStream.available() < 512) {
                    while (i != -1) {
                        i = bufferedInputStream.read();
                        bufferedOutputStream.write(i);
                    }
                    break;
                } else {
                    bufferedInputStream.read(buffer);
                    bufferedOutputStream.write(buffer);
                }
            }
            //强制清空缓冲区的内容
            bufferedOutputStream.flush();
        }
    }
}
