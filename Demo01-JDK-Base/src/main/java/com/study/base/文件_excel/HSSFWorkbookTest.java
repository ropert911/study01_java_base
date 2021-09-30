package com.study.base.文件_excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是老一点的格式   是操作Excel2003以前（包括2003）的版本，扩展名是.xls；
 * 但是此种方式的局限就是导出的行数至多为65535行,超出65536条后系统就会报错。此方式因为行数不足七万行所以一般不会发生内存不足的情况
 * @author sk-qianxiao
 * @date 2021/9/30
 */
public class HSSFWorkbookTest {
    private static final Logger logger = LoggerFactory.getLogger(HSSFWorkbookTest.class);

    public static void main(String[] args) throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("AP");
            //填写Header
            createHeader(sheet);

            int rowIndex = 1;
            while (rowIndex < 1000) {
                rowIndex = exportData(sheet, rowIndex);
            }

            //保存成文件
            saveAsFile(workbook);
        } catch (Exception e) {
            logger.warn("create dev status xls failed.", e);
        }
    }

    private static void createHeader(HSSFSheet sheet) {
        List<String> headerList = new ArrayList(50);
        headerList.add("时间(数据上报截止)");
        headerList.add("WAN口连接介质");
        headerList.add("工作模式（中继/AP/MESH/路由/网关/桥接模式)");
        headerList.add("类型");
        headerList.add("品牌");
        headerList.add("设备型号");
        headerList.add("AP Mac");
        headerList.add("安装位置");
        headerList.add("CPU利用率");
        headerList.add("内存利用率");
        headerList.add("温度");
        headerList.add("在线时长（秒）");
        headerList.add("2.4G在线终端");
        headerList.add("5G在线终端");
        headerList.add("WAN口数");
        headerList.add("WAN口接收流量");
        headerList.add("WAN口发送流量");
        headerList.add("WAN口协商速率");
        headerList.add("WAN口标签（ETH1、ETH2、ETH3）");
        headerList.add("WAN口收发光功率");
        headerList.add("WAN供电状态");
        headerList.add("LAN口数量");
        headerList.add("LAN口标签(LAN1、LAN2、LAN3...LANN)");
        headerList.add("LAN口状态");
        headerList.add("LAN口协商速率(UP/DOWN)");
        headerList.add("LAN口实时流量");
        headerList.add("LAN口下挂终端类型（AP、PC、机顶盒、摄像头、交换机…)");
        headerList.add("LAN口下挂终端品牌");
        headerList.add("LAN口下挂终端型号");
        headerList.add("LAN口MAC地址");
        headerList.add("LAN口在线时长");
        headerList.add("LAN口IP地址");
        headerList.add("2.4G频宽");
        headerList.add("2.4G信道");
        headerList.add("2.4G发送功率");
        headerList.add("2.4G信道可用率");
        headerList.add("5G频宽");
        headerList.add("5G信道");
        headerList.add("5G发送功率");
        headerList.add("5G信道可用率");
        headerList.add("无线终端品牌");
        headerList.add("无线终端型号");
        headerList.add("无线终端MAC地址");
        headerList.add("无线终端信号强度");
        headerList.add("无线终端上行流量（15分钟流量）");
        headerList.add("无线终端下行流量");
        headerList.add("无线终端连接时长（秒）");

        HSSFRow headerRow = sheet.createRow(0);
        for (int cIndex = 0; cIndex < headerList.size(); cIndex++) {
            headerRow.createCell(cIndex).setCellValue(headerList.get(cIndex));
        }
    }


    public static int exportData(HSSFSheet sheet, int rowIndex) {
        HSSFRow rowData = sheet.createRow(rowIndex);
        //时间（数据上报截止）
        rowData.createCell(0).setCellValue("2021-09-30 03:45:46");
        //WAN口连接介质
        rowData.createCell(1).setCellValue("网线");
        //工作模式（中继/AP/MESH/路由/网关/桥接模式）
        rowData.createCell(2).setCellValue("桥接模式");
        //类型
        rowData.createCell(3).setCellValue("iSp");
        //品牌
        rowData.createCell(4).setCellValue("SKSPRUCE");
        //设备型号
        rowData.createCell(5).setCellValue("MSG300");
        //AP Mac
        rowData.createCell(6).setCellValue("112233445566");
        //安装位置
        rowData.createCell(7).setCellValue("北京东城区\"`~!@#$%^&*()_+{}][=-|\\;',.,.:");


        //CPU利用率
        rowData.createCell(8).setCellValue("N/A");
        //内存利用率
        rowData.createCell(9).setCellValue("N/A");
        //温度
        rowData.createCell(10).setCellValue("N/A");
        //在线时长（秒）
        rowData.createCell(11).setCellValue("N/A");
        //2.4G在线终端
        rowData.createCell(12).setCellValue("N/A");
        //5G在线终端
        rowData.createCell(13).setCellValue("N/A");


        //WAN口数
        rowData.createCell(14).setCellValue("N/A");
        //WAN口接收流量
        rowData.createCell(15).setCellValue("N/A");
        //WAN口发送流量
        rowData.createCell(16).setCellValue("N/A");
        //WAN口协商速率
        rowData.createCell(17).setCellValue("N/A");
        //WAN口标签（ETH1、ETH2、ETH3）
        rowData.createCell(18).setCellValue("ETH1");
        //WAN口收发光功率
        rowData.createCell(19).setCellValue("N/A");
        //WAN供电状态
        rowData.createCell(20).setCellValue("N/A");

        //LAN口数量
        rowData.createCell(21).setCellValue("N/A");
        //LAN口标签（LAN1\LAN2\LAN3…..LANN)
        rowData.createCell(22).setCellValue("N/A");
        // LAN口状态
        rowData.createCell(23).setCellValue("N/A");
        //LAN口协商速率(UP/DOWN)
        rowData.createCell(24).setCellValue("N/A");
        //LAN口实时流量
        rowData.createCell(25).setCellValue("N/A");
        //LAN口下挂终端类型（AP、PC、机顶盒、摄像头、交换机…）
        rowData.createCell(26).setCellValue("N/A");
        // LAN口下挂终端品牌
        rowData.createCell(27).setCellValue("N/A");
        //LAN口下挂终端型号
        rowData.createCell(28).setCellValue("N/A");
        //LAN口MAC地址
        rowData.createCell(29).setCellValue("N/A");
        //LAN口在线时长
        rowData.createCell(30).setCellValue("N/A");
        //LAN口IP地址
        rowData.createCell(31).setCellValue("N/A");


        //2.4G频宽
        rowData.createCell(32).setCellValue("N/A");
        //2.4G信道
        rowData.createCell(33).setCellValue("N/A");
        //2.4G发送功率
        rowData.createCell(34).setCellValue("N/A");
        //2.4GG信道可用率
        rowData.createCell(35).setCellValue("N/A");
        //5G频宽
        rowData.createCell(36).setCellValue("N/A");
        //5G信道
        rowData.createCell(37).setCellValue("N/A");
        //5G发送功率
        rowData.createCell(38).setCellValue("N/A");
        //5G信道可用率
        rowData.createCell(39).setCellValue("N/A");


        ///离线及没有终端的默认为 N/A
        //无线终端品牌
        rowData.createCell(40).setCellValue("N/A");
        //线终端型号
        rowData.createCell(41).setCellValue("N/A");
        //无线终端MAC地址
        rowData.createCell(42).setCellValue("N/A");
        //线终端信号强度
        rowData.createCell(43).setCellValue("60dBm;66dBm;57dBm");
        //无线终端上行流量（15 分钟流量）
        rowData.createCell(44).setCellValue("N/A");
        //无线终端下行流量
        rowData.createCell(45).setCellValue("N/A");
        //无线终端连接时长（秒）
        rowData.createCell(46).setCellValue("N/A");

        rowIndex++;
        return rowIndex;
    }


    private static void saveAsFile(HSSFWorkbook workbook) {
        try {
            File tmplFile = new File("C:\\Users\\sk-qianxiao\\Desktop\\HSSFWorkbook.xls");
            try (OutputStream os = new FileOutputStream(tmplFile)) {
                workbook.write(os);
                workbook.close();
            } catch (Exception e) {
                logger.warn("create xslx file failed.", e);
            }
        } catch (Exception e) {
            logger.error("save to db failed");
        }
    }
}
