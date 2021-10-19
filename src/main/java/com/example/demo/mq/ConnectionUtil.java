package com.example.demo.mq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
  /**
   * 建立与RabbitMQ的连接
   * @return
   * @throws Exception
   */
  public static Connection getConnection() throws Exception {
    //定义连接工厂
    ConnectionFactory factory = new ConnectionFactory();
    //设置服务地址
    factory.setHost("localhost");
    //端口
    factory.setPort(5672);
    factory.setUsername("jtang7");
    factory.setPassword("124951");
    factory.setVirtualHost("test");
    // 通过工厂获取连接
    Connection connection = factory.newConnection();
    return connection;
  }

  public static void main(String[] args) throws Exception {
    System.out.println(getConnection());
  }
}