package com.example.demo.readFile;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExcelData {
  private final HSSFWorkbook sheets;

  /**
   * 构造函数，初始化excel数据
   */
  ExcelData(String filePath) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(filePath);
    sheets = new HSSFWorkbook(fileInputStream);
  }

  //打印excel数据
  public Map<String, List<Order>> readExcelData() {
    Map<String, List<Order>> map = new TreeMap<>();
    int nums = sheets.getNumberOfSheets();
    for (int t = 0; t < nums; t++) {
      HSSFSheet sheet = sheets.getSheetAt(t);
      int rows = sheet.getPhysicalNumberOfRows();
      for (int i = 4; i < rows; i++) {
        HSSFRow row = sheet.getRow(i);
        int columns = row.getPhysicalNumberOfCells();
        if (row.getCell(1).toString() == null || row.getCell(1).toString().isEmpty()) {
          break;
        }

        Order order = new Order();
        order.setCategoryId(row.getCell(0).toString());
        order.setName(row.getCell(1).toString());
        order.setType(row.getCell(2).toString());
        order.setNums(Double.parseDouble(row.getCell(3).toString()));
        order.setPrice(Double.parseDouble(row.getCell(4).toString()));
        order.setAllPrice(order.getNums() * order.getPrice());
        order.setTime(sheet.getSheetName());

        List list = map.getOrDefault(order.getName(), new LinkedList<>());
        list.add(order);
        map.put(order.getName(), list);
      }
    }
    return map;
  }

  //测试方法
  public static void main(String[] args) throws IOException {
    ExcelData sheet1 = new ExcelData("D:\\Users\\jtang7\\Desktop\\龙缸直拨 谭在谋.xls");
    Map<String, List<Order>> map = sheet1.readExcelData();
  }
}

