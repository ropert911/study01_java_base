package com.study.base.文件_读写javaio;

import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderWriterTest {
    public static void main(String[] args) throws Exception{
        String data = "1234567890abcdefghijklmnopqrstuvwxyz";
        StringReader stringReader = new StringReader(data);
        int i = stringReader.read();
        System.out.println(i);
        char[] buf = new char[8];
        stringReader.read(buf);
        System.out.println(buf);

        StringWriter stringWriter = new StringWriter();
        stringWriter.write(data);
        StringBuffer stringBuffer = stringWriter.getBuffer();
        System.out.println(stringBuffer.toString());
    }
}
