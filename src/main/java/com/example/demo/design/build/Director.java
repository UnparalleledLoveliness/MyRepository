package com.example.demo.design.build;

public class Director {
  //指挥工人按顺序造房
  public Product create(Builder builder)
  {
    builder.buildA();
    builder.buildB();
    builder.buildC();
    builder.buildD();
    return builder.getProduct();
  }
}
