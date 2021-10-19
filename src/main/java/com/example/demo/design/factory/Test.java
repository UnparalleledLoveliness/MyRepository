package com.example.demo.design.factory;

public class Test {
  public static void main(String[] args) {
    SendFactory factory = new SendFactory();
    Sender sender = factory.produceMail();
    sender.Send();
  }
}
