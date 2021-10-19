package com.example.demo.design.build;

//具体建造者：工人
public class ConcreteBuilder extends Builder {
  private final Product product;

  public ConcreteBuilder() {
    product = new Product();
  }

  @Override
  void buildA() {
    product.setBuildA("地基");
  }

  @Override
  void buildB() {
    product.setBuildB("钢筋");
  }

  @Override
  void buildC() {
    product.setBuildC("铺电线");
  }

  @Override
  void buildD() {
    product.setBuildD("粉刷");
  }

  @Override
  Product getProduct() {
    return product;
  }
}
