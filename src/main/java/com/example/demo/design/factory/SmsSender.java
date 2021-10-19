package com.example.demo.design.factory;

public class SmsSender implements Sender{

  @Override
  public void Send() {
    System.out.println("this is smsSender");
  }
}
