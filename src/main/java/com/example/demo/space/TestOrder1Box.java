package com.example.demo.space;

import static com.example.demo.space.ReadExcel.parseInfoFromInputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOrder1Box {

  public static void main(String[] args) throws IOException {
    double[][] date = parseInfoFromInputFile("D:\\Users\\jtang7\\Desktop\\附件2 需要运输的货物及价格.xlsx", 1);
    // 假设仓库有两款箱子(谁排前面优先用谁)
    List<Map<String, Object>> boxes = new ArrayList<Map<String, Object>>() {
      {
        // 盒子 18*8.45*13.40
        this.add(new HashMap<String, Object>() {
          {
            this.put("id", "1");// 盒子ID
            this.put("code", "1");// 盒子CODE
            this.put("title", "1立方的盒子");// 盒子名称
            this.put("l", 18.00d); // 盒子高度
            this.put("w", 8.45d);// 盒子宽度
            this.put("h", 13.40d);// 盒子高度
          }
        });
      }

    };

    // 订单中的商品列表
    List<Map<String, Object>> order = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<>();
    for (int i = 0; i < date.length; i++) {
      map = new HashMap<>();
      map.put("sku", i);
      map.put("title", i * 100 + "");// 商品名称
      map.put("l", date[i][0]);
      map.put("w", date[i][1]);
      map.put("h", date[i][2]);
      map.put("n", 8);
      map.put("t", 0);
      order.add(map);
    }


  }


}
