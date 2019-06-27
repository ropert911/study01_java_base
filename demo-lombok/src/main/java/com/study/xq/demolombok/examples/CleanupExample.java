package com.study.xq.demolombok.examples;

import lombok.Cleanup;

import java.io.*;

public class CleanupExample {
    public static void main(String[] args) throws IOException {
        //用完后自动关闭
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }
}