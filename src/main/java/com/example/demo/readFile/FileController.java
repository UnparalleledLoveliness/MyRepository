package com.example.demo.readFile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {

  @ResponseBody
  @PostMapping("/read")
  public Map<String, List<Order>> readFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    String path = "D:/test/";
    path=path+fileName;
    File newFile = new File(path);
    file.transferTo(newFile);
    ExcelData data=new ExcelData(path);
    Map<String, List<Order>> map = data.readExcelData();
    return map;
  }

  @RequestMapping("/fileRead")
  public String test() {
    return "updateDemo.html";
  }
}
