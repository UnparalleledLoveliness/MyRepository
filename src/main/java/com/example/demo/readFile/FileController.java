package com.example.demo.readFile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {

  @ResponseBody
  @PostMapping("/read")
  public Map<String, List<Order>> readFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    InputStream inputStream = file.getInputStream();
    ExcelData excelData = new ExcelData(inputStream);

    String path = "D:/test/";
    path = path + fileName;
    File newFile = new File(path);
    file.transferTo(newFile);
    ExcelData data = new ExcelData(path);
    Map<String, List<Order>> map = data.readExcelData();
    return map;
  }

  @ResponseBody
  @PostMapping("/read2")
  public List<People> readFile2(MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    ExcelData excelData = new ExcelData(inputStream);
    return excelData.process();
  }


  @RequestMapping("/fileRead")
  public String test() {
    return "updateDemo.html";
  }

  @RequestMapping("/getFile")
  public File downLoad(HttpServletResponse response) {
    String path = "src/main/resources/1.xlsx";
    try {
      FileInputStream inputStream = new FileInputStream(path);
      byte[] data = new byte[inputStream.available()];
      inputStream.read(data);
      String diskfilename = "1.xlsx";
      response.setContentType("video/avi");
      response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
      System.out.println("data.length " + data.length);
      response.setContentLength(data.length);
      response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
      response.setHeader("Accept-Ranges", "bytes");
      response.setHeader("Etag", "W/\"9767057-1323779115364\"");
      OutputStream os = response.getOutputStream();

      os.write(data);
      //先声明的流后关掉！
      os.flush();
      os.close();
      inputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
