package com.example.demo.space;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
  public static double[][] parseInfoFromInputFile(String inputFilePath, int rowBegin) throws IOException {
    FileInputStream fileInput = new FileInputStream(inputFilePath);//创建文件输入流
    XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
    XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
    int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
    double ans[][] = new double[lastRowNum][3];
    //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
    for (int i = rowBegin; i <= lastRowNum; ++i) {
      XSSFRow row = sheet.getRow(i);//获取每一行
      if (row == null)
        break;
      int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
      double sum=1;
      for (int j = 1; j < 4; ++j) {

        XSSFCell cell = row.getCell(j);//获取每个单元格
        ans[i-1][j-1] = Double.parseDouble(cell.toString());
        sum*=Double.parseDouble(cell.toString());

      }
      System.out.println(sum);
    }
    wb.close();
    fileInput.close();
    return ans;
  }
}

