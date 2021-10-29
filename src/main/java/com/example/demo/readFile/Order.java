package com.example.demo.readFile;

import lombok.Data;

@Data
public class Order {
  private String categoryId;
  private String name;
  private String type;
  private double nums;
  private double price;
  private double allPrice;
  private String time;
}
