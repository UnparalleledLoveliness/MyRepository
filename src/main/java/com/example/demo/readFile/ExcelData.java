package com.example.demo.readFile;

import static org.apache.commons.lang3.BooleanUtils.isTrue;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExcelData {
  private final XSSFWorkbook sheets;

  /**
   * 构造函数，初始化excel数据
   */
  ExcelData(String filePath) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(filePath);
    sheets = new XSSFWorkbook(fileInputStream);
  }

  ExcelData(InputStream fileInputStream) throws IOException {
    sheets = new XSSFWorkbook(fileInputStream);
  }

  //打印excel数据
  public Map<String, List<Order>> readExcelData() {
    Map<String, List<Order>> map = new TreeMap<>();
    int nums = sheets.getNumberOfSheets();
    for (int t = 0; t < nums; t++) {
      XSSFSheet sheet = sheets.getSheetAt(t);
      int rows = sheet.getPhysicalNumberOfRows();
      for (int i = 4; i < rows; i++) {
        XSSFRow row = sheet.getRow(i);
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

  public Map<Integer, People> readAllPeople() {
    Map<Integer, People> map = new LinkedHashMap<>();
    int nums = sheets.getNumberOfSheets();
    for (int t = 0; t < nums; t++) {
      XSSFSheet sheet = sheets.getSheetAt(t);
      int rows = sheet.getPhysicalNumberOfRows();
      for (int i = 1; i < rows; i++) {
        XSSFRow row = sheet.getRow(i);
        People people = new People();
        //1.0转为int
        people.setRank((int) row.getCell(0).getNumericCellValue());
        people.setName(row.getCell(1).toString());
        people.setTimes((int) row.getCell(2).getNumericCellValue());
        people.setDamage((int) row.getCell(3).getNumericCellValue());
        map.put(people.getRank(), people);
      }
    }

    return map;
  }

  //从List<Integer> 中随机出 n 个数
  public static Map<Integer, People> getRandomNums(Map<Integer, People> peopleMap, int n) {
    if (n >= peopleMap.size()) {
      return peopleMap;
    }

    List<Integer> list = new LinkedList<>();
    peopleMap.forEach((k, v) -> list.add(k));

    Map<Integer, People> map = new LinkedHashMap<>();
    for (int i = 0; i < n; i++) {
      int index = (int) (Math.random() * list.size());
      map.put(list.get(index), peopleMap.get(list.get(index)));
      list.remove(index);
    }
    return map;
  }


  //前5名
  public Map<Integer, People> getTop5People(Map<Integer, People> peopleMap) {
    Map<Integer, People> map = new LinkedHashMap<>();
    for (int i = 1; i < 6; i++) {
      map.put(i, peopleMap.get(i));
    }

    return map;
  }

  //从第6到伤害1300w以上
  public Map<Integer, People> getDamagePeople(Map<Integer, People> peopleMap) {
    Map<Integer, People> map = new LinkedHashMap<>();
    for (int i = 6; i < peopleMap.size(); i++) {
      if (peopleMap.get(i).getDamage() >= 1300 || peopleMap.get(i).getTimes() >= 11) {
        map.put(i, peopleMap.get(i));
      }
    }
    return map;
  }

  //抽人
  public Map<Integer, People> getPeople(Map<Integer, People> top5People, Map<Integer, People> damagePeople) {
    Map<Integer, People> top5 = getRandomNums(top5People, 2);
    Map<Integer, People> damage = getRandomNums(damagePeople, 3);
    top5.forEach((k, v) -> v.setIsLucky(true));
    damage.forEach((k, v) -> v.setIsLucky(true));
    Map<Integer, People> result = new LinkedHashMap<>();
    result.putAll(top5);
    result.putAll(damage);
    return result;
  }

  public List<People> process() {
    Map<Integer, People> allPeople = readAllPeople();
    Map<Integer, People> top5People = getTop5People(allPeople);
    Map<Integer, People> damagePeople = getDamagePeople(allPeople);
    Map<Integer, People> luckyPeople = getPeople(top5People, damagePeople);
    Map<Integer, People> unLuckyPeople = new LinkedHashMap<>();
    top5People.forEach((k, v) -> {
      if (!luckyPeople.containsKey(k)) {
        unLuckyPeople.put(k, v);
      }
    });

    damagePeople.forEach((k, v) -> {
      if (!luckyPeople.containsKey(k)) {
        unLuckyPeople.put(k, v);
      }
    });


    divideScore(unLuckyPeople);
    List<People> result = new LinkedList<>();
    result.addAll(top5People.values());
    result.addAll(damagePeople.values());
    //第一步，创建一个workbook对应一个excel文件
    XSSFWorkbook workbook = new XSSFWorkbook();
    //第二部，在workbook中创建一个sheet对应excel中的sheet
    XSSFSheet sheet = workbook.createSheet("自动算分表");
    //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
    XSSFRow row = sheet.createRow(0);
    //第四步，创建单元格，设置表头
    XSSFCell cell = row.createCell(0);
    cell.setCellValue("排名");
    cell = row.createCell(1);
    cell.setCellValue("名字");
    cell = row.createCell(2);
    cell.setCellValue("次数");
    cell = row.createCell(3);
    cell.setCellValue("伤害");
    cell = row.createCell(4);
    cell.setCellValue("积分");
    cell = row.createCell(5);
    cell.setCellValue("备注");

    //第五步，写入数据
    allPeople.forEach((k, v) -> {
      XSSFRow row1 = sheet.createRow(k);
      row1.createCell(0).setCellValue(v.getRank());
      row1.createCell(1).setCellValue(v.getName());
      row1.createCell(2).setCellValue(v.getTimes());
      row1.createCell(3).setCellValue(v.getDamage());
      row1.createCell(4).setCellValue(v.getScore() == null ? 0 : v.getScore());
      row1.createCell(5).setCellValue(isTrue(v.getIsLucky()) ? "抽中躺了哦" :
          (v.getScore() != null && v.getScore() >= 0 ? "抽到积分了哦" : "下次一定"));
    });

    //将文件保存到指定的位置
    try {
      FileOutputStream fos = new FileOutputStream("src/main/resources/1.xlsx");
      workbook.write(fos);
      System.out.println("写入成功");
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  private static void divideScore(Map<Integer, People> unLuckyPeople) {
    int allScore = 36;
    int unLuckySize = unLuckyPeople.size();
    if (unLuckySize >= allScore) {
      //从unLuckySize个人中随机选取allScore个人
      Map<Integer, People> randomNums = getRandomNums(unLuckyPeople, allScore);
      randomNums.forEach((k, v) -> v.setScore(1));
    } else {
      int realSize = (int) (unLuckySize * 0.7);
      Map<Integer, People> randomNums = getRandomNums(unLuckyPeople, realSize);
      int luckNum = 1;
      for (Map.Entry<Integer, People> entry : randomNums.entrySet()) {
        if (allScore <= 0) {
          break;
        }
        entry.getValue().setScore(luckNum);
        if (luckNum == 3) {
          luckNum = 1;
        }
        allScore -= luckNum;
        luckNum++;
      }
    }
  }

  //测试方法
  public static void main(String[] args) throws IOException {
//    ExcelData sheet1 = new ExcelData("D:\\Users\\jtang7\\Desktop\\1.xlsx");
//    List<People> people = sheet1.process();
//    for (People person : people) {
//      System.out.println(person);
//    }
    //从1，2，3，4，5，7，8，11 中抽取5个
    List<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(7);
    list.add(8);
    list.add(11);
    List<Integer> result = new LinkedList<>();
    for (int i = 0; i < 5; i++) {
      int index = (int) (Math.random() * list.size());
      result.add(list.get(index));
      list.remove(index);
    }
    System.out.println(result);
  }
}

