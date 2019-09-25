package com.xq.study.jdk.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExcelParse {
    public List<Error> importDevice(MultipartFile file, Long userId) {
        List<Error> errorList = new ArrayList<>();
        /**********解析EXCEL，暂时未做任何验证，后期需补上**********/

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取sheet
        XSSFSheet itfSheet = workbook.getSheet("ITF");
        XSSFSheet gwSheet = workbook.getSheet("GW");


        //解析header，获取column的list
        XSSFRow itfheader = itfSheet.getRow(0);
        List<String> itfColumnList = new ArrayList<>();             //记录列名
        int itfCellNum = itfheader.getPhysicalNumberOfCells();      //获取列数
        for (int i = 0; i < itfCellNum; i++) {
            itfColumnList.add(getCellString(itfheader, i));
        }

        //获取列数
        int itfRowsNum = itfSheet.getPhysicalNumberOfRows();

        //先把所有的一级区域解析出来，一次性从ism获取需要的区域信息
        Set<String> domainNameSet = new HashSet<>();
        Set<String> itfMacSet = new HashSet<>();
        Set<String> gwIdSet = new HashSet<>();
        Set<String> deviceIdSet = new HashSet<>();

        for (int i = 1; i < itfRowsNum; i++) {
            XSSFRow xssfRow = itfSheet.getRow(i);   //从第二列获取
            String mac = getCellString(xssfRow, 0);
            String gwId = getCellString(xssfRow, 1);
            String domainName = getCellString(xssfRow, 2);
            String deviceId = getCellString(xssfRow, 3);
        }

        return errorList;
    }

    private String getCellString(XSSFRow xssfRow, int index) {
        XSSFCell cell = xssfRow.getCell(index);
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.toString();
    }
}
