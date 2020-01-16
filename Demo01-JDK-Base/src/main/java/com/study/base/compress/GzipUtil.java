package com.study.base.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author sk-qianxiao
 * @date
 */
public class GzipUtil {
    public static final int BUFFER = 1024;

    /**
     * 数据压缩
     *
     * @param data 要压缩的字符数组
     * @return 压缩后的字符数组
     * @throws Exception
     */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        compress(inputStream, outputStream);

        byte[] output = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return output;
    }

    /**
     * 数据压缩
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     * @throws Exception
     */
    private static void compress(InputStream inputStream, OutputStream outputStream)
            throws Exception {

        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);

        int count;
        byte[] data = new byte[BUFFER];
        while ((count = inputStream.read(data, 0, BUFFER)) != -1) {
            gzipOutputStream.write(data, 0, count);
        }

        gzipOutputStream.finish();
        gzipOutputStream.flush();
        gzipOutputStream.close();
    }

    /**
     * 数据解压缩
     *
     * @param data xx
     * @return xx
     * @throws Exception xx
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 解压缩
        decompress(inputStream, outputStream);

        byte[] outData = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return outData;
    }

    /**
     * 数据解压缩
     *
     * @param is xx
     * @param os xx
     * @throws Exception xx
     */
    private static void decompress(InputStream is, OutputStream os)
            throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte[] data = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }

}
