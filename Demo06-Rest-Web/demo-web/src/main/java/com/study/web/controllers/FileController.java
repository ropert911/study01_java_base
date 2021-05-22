package com.study.web.controllers;

import com.study.web.util.FileUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author xq
 */

@Controller
@RequestMapping("/file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Value("${paths.upload.folder}")
    private String uploadFolder;

    @RequestMapping("")
    public String index() {
        logger.info("aaaaaaaaaaa");
        return "/file/index.html";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadFile) {
        try {
            String filename = uploadFile.getOriginalFilename();
            String filepath = Paths.get(uploadFolder, filename).toString();

            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadFile.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse res) throws Exception {
        String fileName = "ap_list.csv";
        String filePath = uploadFolder + "\\" + fileName;

        List<String> contentList = new ArrayList<>();
        contentList.add("11111111111111111");
        contentList.add("222222222222222222");

        FileUtils.createCSV(filePath, fileName, contentList, res);
    }

    //excel下载  注意用swagger下载有问题，和设置头什么有关
    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile2() throws Exception {
        String filename = "导出.xlsx";
        InputStreamResource file = new InputStreamResource(exportReportData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + new String(filename.getBytes("UTF-8"), "iso-8859-1"))
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    //excel下载  注意用swagger下载有问题，和设置头什么有关
    @RequestMapping(path = "/download2", method = RequestMethod.GET)
    public void getFile3(HttpServletResponse res) throws Exception {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            SXSSFSheet sxssfSheet = workbook.createSheet("spot");
            SXSSFRow sxssfRow = sxssfSheet.createRow(0);
            sxssfRow.createCell(0).setCellValue("数据");
            sxssfRow.createCell(1).setCellValue("Data2");
            workbook.createSheet("你不错");

            exportExcel(workbook, "导出.xlsx", res, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public void exportExcel(SXSSFWorkbook workbook, String filename, HttpServletResponse res, String enc) {
        try (OutputStream os = res.getOutputStream();) {
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(enc), "iso-8859-1"));
            workbook.write(os);
            workbook.close();
            workbook.dispose();
            os.flush();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public ByteArrayInputStream exportReportData() {
        //导出指标数据
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            SXSSFSheet sxssfSheet = workbook.createSheet("spot");
            SXSSFRow sxssfRow = sxssfSheet.createRow(0);
            sxssfRow.createCell(0).setCellValue("数据");
            sxssfRow.createCell(1).setCellValue("Data2");
            workbook.createSheet("你不错");

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

}

