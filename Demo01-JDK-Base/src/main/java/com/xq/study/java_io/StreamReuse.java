package com.xq.study.java_io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * 流重置使流可以重复读取
 */
public class StreamReuse {
    private InputStream inputStream;

    public StreamReuse(InputStream inputStream) {
        if (!inputStream.markSupported()) { //不支持重置就用 BuffInputStream 来封闭
            this.inputStream = new BufferedInputStream(inputStream);
        } else {
            this.inputStream = inputStream;
        }
    }

    public InputStream getInputStream() {
        inputStream.mark(Integer.MAX_VALUE);
        return inputStream;
    }

    public void markUsed() throws Exception {
        inputStream.reset();
    }
}

/**
 * 使用保留数组，进一步重建InputStream方式，得到得置能力。 这里用到的是ByteArrayInputStream
 */
class SaveStream {
    private InputStream input;
    private byte[] data = new byte[0];

    public SaveStream(InputStream inputStream) throws Exception {
        this.input = inputStream;
        save();
    }

    private void save() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = input.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        data = outputStream.toByteArray();
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(data);
    }
}
