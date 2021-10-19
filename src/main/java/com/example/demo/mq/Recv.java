package com.example.demo.mq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class Recv {
  private final static String QUEUE_NAME = "simple_queue";

  public static void main(String[] args) throws Exception {
    //获取连接
    Connection connection = ConnectionUtil.getConnection();
    //从连接中创建管道
    Channel channel = connection.createChannel();
    //管道中创建队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    //定义队列的消费者
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("Received: " + new String(body));
        try
        {
          //Thread.sleep(1000);
          Thread.sleep(100000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    channel.basicConsume(QUEUE_NAME,true,consumer);
  }
}

