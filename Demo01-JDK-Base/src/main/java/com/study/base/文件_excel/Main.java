package com.study.base.文件_excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Resource res2 = new ClassPathResource("区域IoT设备导入模板.xlsx");
        InputStream ins2 = res2.getInputStream();

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(ins2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取sheet
        XSSFSheet gwSheet = workbook.getSheet("GW");

        List<String> data = new ArrayList<>();

        //解析header，获取column的list
        XSSFRow gwHeader = gwSheet.getRow(0);
        int itfCellNum = gwHeader.getPhysicalNumberOfCells();
        for (int i = 0; i < itfCellNum; i++) {
            data.add(getCellString(gwHeader, i));
        }
        System.out.println(data);

        //获取列数
        int itfRowsNum = gwSheet.getPhysicalNumberOfRows();
        for (int i = 1; i < itfRowsNum; i++) {
            data.clear();
            //从第二列获取
            XSSFRow xssfRow = gwSheet.getRow(i);
            for (int j = 0; j < itfCellNum; j++) {
                data.add(getCellString(xssfRow, j));
            }
            System.out.println(data);
        }
    }

    private static String getCellString(XSSFRow xssfRow, int index) {
        XSSFCell cell = xssfRow.getCell(index);
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.toString();
    }
}
