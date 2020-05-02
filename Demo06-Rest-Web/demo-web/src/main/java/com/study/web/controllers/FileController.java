package com.study.web.controllers;

import com.study.web.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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


}

