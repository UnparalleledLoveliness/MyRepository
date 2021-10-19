package com.example.demo.design.build;

//抽象建造者
abstract class Builder {
  //地基
  abstract void buildA();
  //钢筋工程
  abstract void buildB();
  //铺电线
  abstract void buildC();
  //粉刷
  abstract void buildD();
  //完工-获取产品
  abstract Product getProduct();
}
