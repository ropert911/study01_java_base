package com.study.base.文件_读写javaio;

import java.io.*;

public class PipedWriterReaderTest {
    public static void main(String[] args) {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        Producer producer = new Producer(writer);
        Consumer consumer = new Consumer(reader);

        try {
            writer.connect(reader);
            producer.start();
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Producer extends Thread {
    //输出流
    private PipedWriter writer = new PipedWriter();
    public Producer(PipedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Hello World!");
            writer.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Consumer extends Thread{
    //输入流
    private PipedReader reader = new PipedReader();

    public Consumer(PipedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            char [] cbuf = new char[20];
            reader.read(cbuf, 0, cbuf.length);
            System.out.println("管道流中的数据为: " + new String(cbuf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}