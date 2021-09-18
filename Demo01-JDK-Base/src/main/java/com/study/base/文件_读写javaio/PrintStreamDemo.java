package com.study.base.文件_读写javaio;

import java.io.*;

public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        final String fileName = "C:\\Users\\Administrator\\Desktop\\java.txt";
        File file = new File(fileName);
        testPrintMethod(fileName, file);
        testOtherMethod(fileName,file);
    }

    private static void testOtherMethod(String fileName, File file) throws IOException {
        PrintStream ps = new PrintStream(fileName);
        ps.write("helloworld".getBytes());
        ps.println();
        ps.format("文件名称:%s", file.getName());
        ps.println();
        ps.write(0x41);
        ps.append("abcde");
        ps.close();

    }

    private static void testPrintMethod(final String fileName, File file) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileName));
        ps.println('a');
        ps.println("hello");
        ps.println(2345);
        ps.print(3.1415);
        ps.println();//写入换行符.
        ps.printf("文件名称:%s,是否可读:%s", file.getName(),file.canRead());
        ps.println();
        ps.close();
    }
}
