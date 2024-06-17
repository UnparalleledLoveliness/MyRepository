package com.example.demo.readFile;

import lombok.Data;

@Data
public class People {
  //排名，名字，次数，伤害
  private Integer rank;
  private String name;
  private Integer times;
  private Integer damage;
  private Integer score;
  private Boolean isLucky;
}
