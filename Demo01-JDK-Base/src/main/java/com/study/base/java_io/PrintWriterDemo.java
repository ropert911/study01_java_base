package com.study.base.java_io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintWriterDemo {
    public static void main(String[] args) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("C:\\Users\\Administrator\\Desktop\\aaa.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pw.print("Hello World");
        pw.close();
    }
}
