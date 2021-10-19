package com.example.demo.design.factory;


/**
 * 工厂模式适合：出现了大量的产品需要创建，并且具有共通的接口时，其实就是实现接口
 * 优点：客户端不需要创建对象，明确了各个类的职责
 * 缺点：该工程负责创建所有实例，如果有新的类加入，需要不断的修改工厂类，不利于后期维护
 */
public class SendFactory {
  public Sender produceMail() {
    return new MailSender();
  }

  public Sender produceSms() {
    return new SmsSender();
  }
}
