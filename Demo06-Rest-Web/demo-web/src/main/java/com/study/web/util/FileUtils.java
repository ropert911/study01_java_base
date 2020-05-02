package com.study.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author xq
 * @data 2020/5/2
 **/
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void createCSV(String filePath, String fileName, List<String> rows, HttpServletResponse res) throws Exception {
        File csvFile = null;
        BufferedWriter csvWriter = null;
        try {
            csvFile = new File(filePath);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

            // 写入文件内容
            for (String row : rows) {
                csvWriter.write(row);
                csvWriter.newLine();
            }
            csvWriter.flush();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);

        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = new FileInputStream(filePath);
            bis = new BufferedInputStream(is);

            int length = 0;
            byte[] temp = new byte[1 * 1024 * 10];

            while ((length = bis.read(temp)) != -1) {
                bos.write(temp, 0, length);
            }
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            bos.flush();
            bis.close();
            bos.close();
            is.close();
            if (csvFile.exists()) {
                csvFile.delete();
            }
        }
    }
}
